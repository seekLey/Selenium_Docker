# Use an official Maven image as a parent image
FROM maven:3.8.4-openjdk-11

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the Maven project files into the container
COPY ./ /usr/src/app/

# Set up ChromeOptions for headless mode
RUN apt-get update && \
    apt-get install -y \
    wget \
    unzip \
    libnss3 \
    libgconf-2-4 \
    libfontconfig1 && \
    apt-get install -y chromium

# Fetch the latest version of ChromeDriver
RUN CHROMEDRIVER_VERSION=$(curl -sS https://chromedriver.storage.googleapis.com/LATEST_RELEASE) && \
    wget https://chromedriver.storage.googleapis.com/${CHROMEDRIVER_VERSION}/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip -d /usr/local/bin/ && \
    rm chromedriver_linux64.zip

# Build the Maven project
RUN mvn clean install || { echo "Maven build failed"; exit 1; }

# Define the command to run your application
CMD ["mvn", "test"]
