import redis
from .services import parser

redis_client = redis.Redis(host='localhost', port=6379)
def publish(channel, type, content):
    while True:
        # type = input()
        # content = input()
        message = '{"type": "' + type + '", "content":"' + content + '"}'
        print(message)
        redis_client.publish(channel, message)

def subscribe(topic):
    pubsub = redis_client.pubsub()
    pubsub.subscribe(topic)
    for message in pubsub.listen():
        if message['type'] == 'message':
            # UTF-8로 디코딩하여 출력
            data = message['data'].decode('utf-8')
            print(f"Received message: {data}")
            parser(data)