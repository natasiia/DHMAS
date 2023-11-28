import datetime
import json
import websocket
import csv
import time
import threading

# Path to patient data
csv_file_path = 'patient_data.csv'

# WebSocket endpoint URL
ws_url = 'ws://localhost:8080/pdcs'  

def on_open(ws):
    send_patient_data(ws)

def on_error(ws, error):
    print(f"WebSocket error: {error}")

def on_close(ws, close_status_code, close_reason):
    print(f"WebSocket connection closed: {close_status_code}, {close_reason}")

def send_patient_data(ws):
    try:
        with open(csv_file_path, newline='') as csvfile:
            reader = csv.DictReader(csvfile)

            batch = []
            for row in reader:
                row['date'] = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                batch.append(row)
                if len(batch) == 5:
                    ws.send(json.dumps(batch))
                    print(f"Sent batch: {json.dumps(batch)}")
                    batch = []  
                    time.sleep(180) 
            if batch:
                ws.send(json.dumps(batch))
                print(f"Sent last batch: {json.dumps(batch)}")
    except Exception as e:
        print(f"An error occurred: {e}")

def run_websocket_client():
    ws = websocket.WebSocketApp(ws_url,
                                on_open=on_open,
                                on_error=on_error,
                                on_close=on_close)
    ws.run_forever()

print("Running WebSocket client...")
run_websocket_client()