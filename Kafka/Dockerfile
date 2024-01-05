FROM python:3.8

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the Python script and the shell script into the container
COPY user.py .
COPY start_scripts.sh .

# Copy the requirements file
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
ENV PYTHONUNBUFFERED=1

# Run start_scripts.sh when the container launches
CMD ["./start_scripts.sh"]
