#!/bin/bash

TIME_LIMIT_SEC=${TIME_LIMIT_SEC:-1}
MEMORY_LIMIT_MB=${MEMORY_LIMIT_MB:-512}
RESULT_FILE="result.json"

compile_error() {
    local error_msg="$1"
    jq -c -n --arg msg "$error_msg" '{"compile_error": $msg}' > "$RESULT_FILE"
    exit 0
}

javac Main.java 2> compile_err.log || {
    error_text=$(cat compile_err.log)
    compile_error "$error_text"
}
rm -f compile_err.log

json_objects=()
test_files=( $(ls -1 *.in 2>/dev/null | sort -n -t. -k1) )
for test_file in "${test_files[@]}"; do
    test_num="${test_file%.in}"
    stdout_file="tmp_${test_num}_stdout.txt"
    stderr_file="tmp_${test_num}_stderr.txt"
    time_file="tmp_${test_num}_time.txt"
    rm -f "$stdout_file" "$stderr_file" "$time_file"

    {
        /usr/bin/time -o "$time_file" -f "%e" timeout "$TIME_LIMIT_SEC" java -Xmx${MEMORY_LIMIT_MB}m Main < "$test_file" > "$stdout_file" 2> "$stderr_file"
    }
    exit_code=$?

    if [ -f "$time_file" ]; then
        exec_time=$(tail -n1 "$time_file" 2>/dev/null || echo "0.00")
        rm -f "$time_file"
    else
        exec_time="0.00"
    fi

    status="ok"
    if [ $exit_code -eq 124 ]; then
        status="timeout"
    elif [ $exit_code -ne 0 ]; then
        status="runtime_error"
    fi

    stdout_content=$(cat "$stdout_file" 2>/dev/null || echo "")
    stderr_content=$(cat "$stderr_file" 2>/dev/null || echo "")

    obj=$(jq -c -n --arg num "$test_num" --arg stdout "$stdout_content" --arg stderr "$stderr_content" --arg status "$status" --arg time "$exec_time" '{test_num: $num, stdout: $stdout, stderr: $stderr, status: $status, execution_time_sec: ($time | tonumber)}')
    json_objects+=("$obj")

    rm -f "$stdout_file" "$stderr_file"
done

printf '%s\n' "${json_objects[@]}" | jq -s '.' > "$RESULT_FILE"
exit 0