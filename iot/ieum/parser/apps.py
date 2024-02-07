from django.apps import AppConfig
import threading
from api.services import activeCheck
from ieum.keys import get_key

class ParserConfig(AppConfig):
    default_auto_field = 'django.db.models.BigAutoField'
    name = 'parser'

    def ready(self):
        activeCheck()
        # dataCheck()
        from .sub import subscribe
        sub_thread = threading.Thread(target=subscribe, args=(get_key("SERIAL_CODE"),))
        sub_thread.start()