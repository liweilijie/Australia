# Australia
my dream about Australia.

## java

[install](https://docs.datastax.com/en/jdk-install/doc/jdk-install/installOpenJdkDeb.html):

```bash
# install OpenJDK8
sudo apt-get update
sudo apt-get install openjdk-8-jdk
java -version


# show the java multi version and the path.
sudo update-alternatives --config java

# input to ~/.bashrc or /etc/environment to setting JAVA_HOME
# java
export JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java"
source ~/.bashrc
```

mac:

```bash
# 查看系统里面java的版本以及路径
/usr/libexec/java_home -V

# 切换到对应的JDK版本，只需要将JAVA_HOME环境变量为JDK安装路径即可，vim ~/.zshrc
export JAVA_HOME=/Users/liwei/Library/Java/JavaVirtualMachines/corretto-1.8.0_412/Contents/Home
```

## python

WFD download firefly audios

```python
import json
import os
import time
from urllib.request import urlretrieve

import requests
import base64
import pprint

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36',
    'Token': 'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ5aGMtMjAxOSIsImlhdCI6MTcyMDE4Nzg2Nywic3ViIjoie1widXNlcklkXCI6XCIzZTQyMDU4MWIxM2JmM2I3N2JiOGI3MDg3MTRlMjA1MVwifSIsImV4cCI6MTcyMDc5MjY2N30.PhhYKhBGgVHnHMlUiXt0EefH0esyysxHXXOa65gdMaI',
    'S': 'N7D8XfwWdfEywID/U0fMACfZa346xleDUENWAeb82O0C4uTfvKpUxoIoW2zlIzB+JBcHNeBH1JpOmMJXCawtzICaNmod3b+TSB655vWTUskwL9kWe8rd4sbEAA/PV+kWaCQbYFi6P0Ng2M4K8Oh5CDmHxYkO0UQHx9vw6tgNp+s=',
}

for i in range(1, 188):
    url = 'https://api.fireflyau.com/apiWeb/pte/prediction/predictionWeekVideo/getListOne?isJJ=&isYC=&keyWord=&pageNo={num}&rank=7&degree=&typeId=&tagSite=&tagTopic=&tagSentence=&wordsCount=&id=&questionType=LWFD&planId='.format(num=i)
    try:
        print(url)
        response = requests.get(url=url, headers=headers)
        if response.status_code == 200:
            print('success ' + str(i))
            # print(response.text)
            # print(response.json().get('result'))
            result = response.json().get('result')
            print(result)
            text = base64.b64decode(result)
            json_data = json.loads(text)
            current = json_data.get('current')
            audios = json_data.get('records')[0].get('questionAudios')
            answer_info = json_data.get('records')[0].get('answerInfo')
            pprint.pprint(audios, compact=True)
            for item in audios:
                print(item['audioUrl'])
                filename = str(current) + '-' + answer_info+item['title']+'.mp3'
                filepath = os.path.join('fireflywfd', filename)
                urlretrieve(item['audioUrl'], filepath)
                time.sleep(1)
    except requests.RequestException:
        print('error')
        print(requests.exceptions)
```

result:

```json
{
  "current": 10,
  "pages": 187,
  "records": [
    {
      "qNum": "131020",
      "tag1": 1,
      "question": "https://upload.fireflyau.com/pte/LWFD/r/b9a8c5231aa37f105af65e09ca33fd61.mp3",
      "unavailable": 1,
      "tagSentence": "宾表从句",
      "answerInfo": "Our view is that educational reforms have been inadequately implemented.",
      "degree": 3,
      "remark": "",
      "title": "20",
      "isForecast": 1,
      "tag4": 0,
      "questionAudios": [
        {
          "audioUrl": "https://upload.fireflyau.com/pte/LWFD/r/04ef634a0c4c1094d8b4d58a868b7a32.mp3",
          "questionId": "2c908087672b1edc01672e7067a90033",
          "title": "正常难度（女2）",
          "type": 2
        },
        {
          "audioUrl": "https://upload.fireflyau.com/pte/LWFD/r/aed5ee751a6abee072900b7347fc972c.mp3",
          "questionId": "2c908087672b1edc01672e7067a90033",
          "title": "正常难度（男1）",
          "type": 3
        },
        {
          "audioUrl": "https://upload.fireflyau.com/pte/LWFD/r/58bf68e9a10a352fccc1d8557a091432.mp3",
          "questionId": "2c908087672b1edc01672e7067a90033",
          "title": "正常难度（男2）",
          "type": 4
        },
        {
          "audioUrl": "https://upload.fireflyau.com/pte/LWFD/r/7c2f1cd9ecc18894288237ed13e5362a.mp3",
          "questionId": "2c908087672b1edc01672e7067a90033",
          "title": "挑战难度（女）",
          "type": 5
        }
      ],
      "tag2": 0,
      "tag3": 0,
      "answerInfo2": "我们的看法是，教育改革执行得不够充分。",
      "videoUrl": null,
      "questionRecord": "https://upload.fireflyau.com/pte/LWFD/r/04ef634a0c4c1094d8b4d58a868b7a32.mp3",
      "id": "2c908087672b1edc01672e7067a90033",
      "isJJ": 1,
      "isInsert": 0,
      "isUpdate": 0
    }
  ],
  "searchCount": true,
  "size": 1,
  "total": 187
}
```


## 待学习

- [springboot-guide](https://github.com/CodingDocs/springboot-guide)
- [一套全面又有实际意义的axios封装+api管理方案](https://github.com/slevin57/Blog/issues/11)
