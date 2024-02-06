import redis
from .services import parser
from ieum.keys import get_key

redis_client = redis.Redis(host='localhost', port=6379)

def publish():
    while True:
        message = input()
        redis_client.publish(get_key("SERIAL_CODE"), message)


def subscribe(topic):
    pubsub = redis_client.pubsub()
    pubsub.subscribe(topic)
    for message in pubsub.listen():
        if message['type'] == 'message':
            # UTF-8로 디코딩하여 출력
            data = message['data'].decode('utf-8')
            print(f"Received message: {data}")
            parser(data)