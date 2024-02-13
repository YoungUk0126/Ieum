# Generated by Django 5.0.1 on 2024-02-12 23:45

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('parser', '0004_alter_pilltime_pilltime'),
    ]

    operations = [
        migrations.CreateModel(
            name='AnyEvent',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('eventName', models.CharField(max_length=30)),
                ('eventDateTime', models.DateField(default=datetime.date.today)),
            ],
        ),
        migrations.RenameField(
            model_name='pilltime',
            old_name='Pill',
            new_name='pill',
        ),
    ]
