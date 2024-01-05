#!/bin/bash

# Start 10 instances of the Python script in the background
for i in {1..10}
do
   python user.py $i &
done

# Wait for all background processes to finish
wait
