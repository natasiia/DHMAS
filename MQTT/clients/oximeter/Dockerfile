FROM python:3.9-alpine

# Set the working directory in the container
WORKDIR /usr/src/app

# Install only the necessary dependencies
COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

# Copy the Python script into the container
COPY . .

# Command to run the script
CMD [ "python", "./oximeter.py" ]