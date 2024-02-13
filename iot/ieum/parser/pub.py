from ieum.keys import get_key
import redis

redis_client = redis.Redis(host="i10a303.p.ssafy.io", port=6379)
def publish(type, content):
    channel = get_key("SERIAL_CODE")
    message = '{"type": "' + type + '", "content":"' + content + '"}'
    redis_client.publish(channel, message)