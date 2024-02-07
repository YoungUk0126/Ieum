from ieum.keys import get_key
import redis

redis_client = redis.Redis(host='localhost', port=6379)
def publish(type, content):
    channel = get_key("SERIAL_CODE")
    message = '{"type": "' + type + '", "content":"' + content + '"}'
    print(message)
    redis_client.publish(channel, message)