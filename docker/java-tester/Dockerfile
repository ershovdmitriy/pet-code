FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

RUN apt-get update && apt-get install -y time jq && rm -rf /var/lib/apt/lists/*

RUN useradd -m -u 1000 runner
USER runner

WORKDIR /app

COPY --chmod=755 runner.sh /runner.sh

ENTRYPOINT ["/runner.sh"]