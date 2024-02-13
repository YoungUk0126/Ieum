import redis
from .services import parser
from ieum.keys import get_key
redis_client = redis.Redis(host="i10a303.p.ssafy.io", port=6379)

def subscribe(topic):
    pubsub = redis_client.pubsub()
    pubsub.subscribe(topic)
    for message in pubsub.listen():
        if message['type'] == 'message':
            # UTF-8로 디코딩하여 출력
            data = message['data'].decode('utf-8')
            print(f"Received message: {data}")
            parser(data)