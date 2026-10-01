# 1. Official Maven image with OpenJDK 19 / Java base
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Layihə üçün işçi qovluğu
WORKDIR /app

# 2. Chrome brauzerini konteynerə quraşdırırıq (Headless test icrası üçün)
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    unzip \
    curl \
    libgconf-2-4 \
    libnss3 \
    libxss1 \
    libasound2 \
    fonts-liberation \
    xdg-utils \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# 3. Layihənin pom.xml və mənbə kodlarını kopyalayırıq
COPY pom.xml .
COPY testng.xml .
COPY src ./src

# 4. Asılılıqları (dependencies) əvvəlcədən yükləyirik
RUN mvn dependency:go-offline -B

# 5. Konteyner işə düşəndə testləri headless rejimdə icra edən əmr
ENTRYPOINT ["mvn", "clean", "test", "-Dheadless=true"]