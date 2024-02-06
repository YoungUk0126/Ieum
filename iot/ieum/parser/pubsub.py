import redis
import webbrowser
from .services import parser
def subscribe(topic):
    redis_client = redis.Redis(host='localhost', port=6379)
    pubsub = redis_client.pubsub()
    pubsub.subscribe(topic)
    for message in pubsub.listen():
        if message['type'] == 'message':
            # UTF-8로 디코딩하여 출력
            data = message['data'].decode('utf-8')
            print(f"Received message: {data}")
            parser(data)