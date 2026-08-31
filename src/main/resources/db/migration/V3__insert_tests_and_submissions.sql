DROP TABLE IF EXISTS solved_tasks;

CREATE TABLE IF NOT EXISTS submissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    task_id BIGINT NOT NULL,
    code TEXT NOT NULL,
    status VARCHAR(20) NOT NULL, -- PENDING, RUNNING, ACCEPTED, WRONG_ANSWER, RUNTIME_ERROR, TIME_LIMIT_EXCEEDED, COMPILATION_ERROR
    result TEXT,
    execution_time_ms BIGINT DEFAULT NULL,
    executed_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE
);

CREATE INDEX idx_submissions_user_task ON submissions(user_id, task_id);

CREATE TABLE IF NOT EXISTS test_cases (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    task_id BIGINT NOT NULL,
    input_data TEXT NOT NULL,
    expected_output TEXT NOT NULL,
    is_public BOOLEAN DEFAULT FALSE,
    sort_order INT DEFAULT 0,
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE
);

CREATE INDEX idx_test_cases_task_order ON test_cases(task_id, sort_order);

INSERT INTO test_cases (task_id, input_data, expected_output, is_public, sort_order) VALUES
    (1, '2 7 11 15\n9', '0 1', TRUE, 1),
    (1, '3 2 4\n6', '1 2', FALSE, 2),
    (1, '3 3\n6', '0 1', FALSE, 3),
    (2, 'hello', 'olleh', TRUE, 1),
    (2, 'world', 'dlrow', FALSE, 2),
    (2, 'a', 'a', FALSE, 3),
    (3, 'radar', 'true', TRUE, 1),
    (3, 'hello', 'false', FALSE, 2),
    (3, 'level', 'true', FALSE, 3),
    (4, '-2 1 -3 4 -1 2 1 -5 4', '6', TRUE, 1),
    (4, '1', '1', FALSE, 2),
    (4, '5 4 -1 7 8', '23', FALSE, 3),
    (5, '1 2 3 4 5\n3', '2', TRUE, 1),
    (5, '1 2 3 4 5\n6', '-1', FALSE, 2),
    (5, '5 6 7 8 9\n5', '0', FALSE, 3);