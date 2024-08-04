# Australia
my dream about Australia.

哈哈哈，精心备战了2个多月我的PTE终于考过了，刚刚好雅思6分首考过了，太开心了，如果再给我一个月七炸应该没问题的，不过不重要了，我要求没有那么高。
接下来全身心投入到java的世界里面啦。

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

WE download from firefly: involved the questions and answers, but also include the mp4 video.

```python
import json
import os
import time
from urllib.request import urlretrieve
import markdownify
import requests
import base64
import re

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36',
    'Token': 'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ5aGMtMjAxOSIsImlhdCI6MTcyMDY5NjY0Nywic3ViIjoie1widXNlcklkXCI6XCIzZTQyMDU4MWIxM2JmM2I3N2JiOGI3MDg3MTRlMjA1MVwifSIsImV4cCI6MTcyMTMwMTQ0N30.LcBVOm05UFusX5dNlvKbw_VNr1mtNzFO8JY40yJQF0g',
    'S': 'E1nnoEnTqv1hGIlzcksqOCFcDxitSNqU3ZTpvUiAp7B9N0hfoR1L/YqFAiZJ8bY87EF+KGwVhWs1ROH5/wjGGu8QeMrGf/UH0Iu2CzFLfHYbD5MDVTum3wq/+xhBPgbwvSp+2ip0ffMqP+sZRSDcCK6+l32dIpI4OO6Mh6iU/nU=',
}

for i in range(1, 44):
    url = 'https://api.fireflyau.com/apiWeb/pte/prediction/predictionWeekVideo/getListOne?isJJ=&isYC=&keyWord=&pageNo={num}&rank=7&degree=&typeId=&tagSite=&tagTopic=&tagSentence=&wordsCount=&id=&questionType=WWE&planId='.format(num=i)
    try:
        print(url)
        response = requests.get(url=url, headers=headers)
        if response.status_code == 200:
            print('success ' + str(i))
            # print(response.text)
            # print(response.json().get('result'))
            result = response.json().get('result')
            # print(result)
            text = base64.b64decode(result)
            json_data = json.loads(text)
            current = json_data.get('current')
            title = json_data.get('records')[0].get('title')
            question_info = json_data.get('records')[0].get('questionInfo')
            question_text = json_data.get('records')[0].get('questionText')
            video_url = json_data.get('records')[0].get('videoUrl') or ""
            # print(title)
            # print(video_url)
            # print(question_info)
            # print(question_text)
            # print(question_text)
            # delete multiple new line
            h = markdownify.markdownify(question_text, heading_style="ATX")
            text = re.sub(r'^\s+$', '\n', h, flags=re.MULTILINE)
            # print(h)
            # pprint.pprint(audios, compact=True)
            with open('we.md', 'a+') as f:
                f.write('\n## ' + str(current) + '. ' + title + '\n')
                f.write('**' + question_info + '**' + '\n')
                f.write(text)
            if video_url.endswith(".mp4"):
                print(str(current) + '. ' + video_url)
                print('downloading video...')
                # must process file name illegality
                rstr = r"[\/\\\:\*\?\"\<\>\|]"  # '/ \ : * ? " < > |'
                new_title = re.sub(rstr, "_", title)  # the illegality character replace to _
                filename = str(current) + "-" + new_title + ".mp4"
                filepath = os.path.join('fireflywe', filename)
                urlretrieve(video_url, filepath)
                print('video downloaded')
                time.sleep(2)
            else:
                print('video_url illegality')
                print(video_url)
    except requests.RequestException:
        print('error')
        print(requests.exceptions)
```

**result**:

```json
{
  "current": 1,
  "pages": 43,
  "records": [
    {
      "qNum": "101001",
      "tag1": 1,
      "unavailable": 1,
      "answerInfo": "There is an ongoing debate on the timing of getting married. Some people claim that getting married before being financially independent is not a good idea, whereas some disagree. Personally speaking, I strongly agree that getting married before finishing school or getting a job is foolish. \n\nThe first and the most important reason for my belief is that getting married before completing school or getting a job can cause a financial burden because couples do not have enough income to support their life. To be more specific, various aspects of life incur costs for married couples when supporting a family, such as buying a house or purchasing a vehicle. This can be established by a recent study by Oxford University, which stated that 71.3% of the couples who got married before finishing school or getting a job ended up with divorce as they do not have enough income to support their living costs.\n\nOn the other hand, the second reason for this argument is that getting married before finishing school or getting a job can limit people's social networks, which is harmful to their future career development. To illustrate, those who are married must spend more time on family affairs than socializing with people from schools or the professional field, which deprives them of opportunities to obtain new information that is conducive to the development of their study or career. According to the annual report of the Australian government, more than 70% of the citizens stated that getting married before finishing their education or getting a job could influence their social networks negatively.\n\nWith all the points above, the conclusion can be made that getting married before completing study or getting a job is irrational due to the creation of financial burden and limitation of people's social networks.\n",
      "remark": "",
      "title": "毕业或工作前就结婚",
      "questionInfo": "It is argued that getting married before finishing school or getting a job is foolish. To what extent do you agree or disagree? \n【有人认为，在完成学业或找到工作之前结婚是愚蠢的。 你在多大程度上同意或不同意?  】",
      "isForecast": 1,
      "tag4": 0,
      "questionText": "<p style=\"margin: 0px 0px 6px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Helvetica;\"><span style=\"font-stretch: normal; line-height: normal; font-family: 'PingFang SC';\"><strong>观点</strong></span><strong>1 &ndash; </strong><span style=\"font-stretch: normal; line-height: normal; font-family: 'PingFang SC';\"><strong>同意</strong></span></p>\n<p style=\"margin: 0px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Personally speaking, getting married before finishing school or getting a job is foolish.</p>\n<p style=\"margin: 0px; font-stretch: normal; font-size: 11px; line-height: normal; font-family: Times; min-height: 13px;\">&nbsp;</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">论点<span style=\"font-stretch: normal; line-height: normal; font-family: Helvetica;\">1 - </span>耽误学业</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Getting married before graduating from school or starting a career can negatively affect individuals&rsquo; academic performance</p>\n<p style=\"margin: 6px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">参考词汇</p>\n<p style=\"margin: 0px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Studies/ Waste time/ Distracting, Distracted/ Lose concentration</p>\n<p style=\"margin: 0px; font-stretch: normal; font-size: 11px; line-height: normal; font-family: Times; min-height: 13px;\">&nbsp;</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">论点<span style=\"font-stretch: normal; line-height: normal; font-family: Helvetica;\">2 &ndash; </span>增加家庭财务压力</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Getting married before graduating from school or starting a career can increase the financial burden for couples</p>\n<p style=\"margin: 6px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">参考词汇</p>\n<p style=\"margin: 0px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Daily expenses/ Living expenses/ Pay bills/ Make ends meet</p>\n<p style=\"margin: 0px; font-stretch: normal; font-size: 11px; line-height: normal; font-family: Times; min-height: 13px;\">&nbsp;</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">论点<span style=\"font-stretch: normal; line-height: normal; font-family: Helvetica;\">3 &ndash; </span>导致离婚率上升</p>\n<p style=\"margin: 0px 0px 0px 9px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Getting married before graduating from school or starting a career may drive up the divorce rate.</p>\n<p style=\"margin: 6px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">参考词汇</p>\n<p style=\"margin: 0px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Mentally immature/ Conflicts/ Leave no time or energy to cope with</p>\n<p style=\"margin: 0px; font-stretch: normal; font-size: 11px; line-height: normal; font-family: Times; min-height: 13px;\">&nbsp;</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">论点<span style=\"font-stretch: normal; line-height: normal; font-family: Helvetica;\">4 &ndash; </span>阻碍事业的发展</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Getting married before graduating from school or starting a career may have detrimental influence on future professional development<span style=\"font-stretch: normal; line-height: normal; font-family: Helvetica;\">.</span></p>\n<p style=\"margin: 6px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">参考词汇</p>\n<p style=\"margin: 0px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Household chores/ Feel distressed/ Trivial stuff to handle</p>\n<p style=\"margin: 0px; font-stretch: normal; font-size: 11px; line-height: normal; font-family: Times; min-height: 13px;\">&nbsp;</p>\n<p style=\"margin: 0px; font-stretch: normal; font-size: 11px; line-height: normal; font-family: Times; min-height: 13px;\">&nbsp;</p>\n<p style=\"margin: 0px 0px 6px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\"><strong>观点</strong><span style=\"font-stretch: normal; line-height: normal; font-family: Helvetica;\"><strong>2 &ndash; </strong></span><strong>不同意</strong></p>\n<p style=\"margin: 0px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Personally speaking, getting married before finishing school or getting a job is not foolish.</p>\n<p style=\"margin: 0px; font-stretch: normal; font-size: 11px; line-height: normal; font-family: Times; min-height: 13px;\">&nbsp;</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">论点<span style=\"font-stretch: normal; line-height: normal; font-family: Helvetica;\">1 &ndash; </span>培养责任心</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Getting married before finishing school or entering the workforce can help people cultivate their sense of responsibility<span style=\"font-stretch: normal; line-height: normal; font-family: Helvetica;\">.</span></p>\n<p style=\"margin: 6px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">参考词汇</p>\n<p style=\"margin: 0px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Accountability, accountable/ Reliability<span style=\"font-stretch: normal; line-height: normal; font-family: 'PingFang SC';\">，</span>reliable/ Learn to take the responsibility/ Mature/ Distracted</p>\n<p style=\"margin: 0px; font-stretch: normal; font-size: 11px; line-height: normal; font-family: Times; min-height: 13px;\">&nbsp;</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">论点<span style=\"font-stretch: normal; line-height: normal; font-family: Helvetica;\">2 &ndash; </span>给予精神支撑去面对生活压力</p>\n<p style=\"margin: 0px 0px 0px 9px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Getting married before finishing school or entering the workforce can help people to cope with life pressure.</p>\n<p style=\"margin: 6px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">参考词汇</p>\n<p style=\"margin: 0px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Psychological support/ The initial recipients of the information/ Talk with/ Take care of each other/ Tell troubles/ Release pressure</p>\n<p style=\"margin: 0px; font-stretch: normal; font-size: 11px; line-height: normal; font-family: Times; min-height: 13px;\">&nbsp;</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">论点<span style=\"font-stretch: normal; line-height: normal; font-family: Helvetica;\">3 &ndash; </span>有利于事业和学习的发展</p>\n<p style=\"margin: 0px 0px 0px 9px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Getting married before finishing school or entering the workforce can make people concentrated on their career and academic goals.</p>\n<p style=\"margin: 6px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">参考词汇</p>\n<p style=\"margin: 0px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Stable relationship/ Share house chores/ Partner support/ Financial support/ Because of the strong family support.</p>\n<p style=\"margin: 0px; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">&nbsp;</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">论点<span style=\"font-stretch: normal; line-height: normal; font-family: Helvetica;\">4 &ndash; </span>有利于精神健康</p>\n<p style=\"margin: 0px 0px 0px 9.3px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Getting married before finishing school or entering the workforce can help with mental health.</p>\n<p style=\"margin: 6px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: 'PingFang SC';\">参考词汇</p>\n<p style=\"margin: 0px 0px 0px 18px; text-align: justify; font-stretch: normal; font-size: 12px; line-height: normal; font-family: Times;\">Increase life happiness/ Stable marital relationships/ Someone with share happiness and sorrows</p>\n<p>&nbsp;</p>",
      "tag2": 0,
      "tag3": 0,
      "videoUrl": "https://upload.fireflyau.com/pte/WESSAY/v/baaea539c9a949b1cb556db02ed0898c.mp4",
      "id": "2c908087673522000167487b102e013d",
      "isJJ": 1,
      "isInsert": 0,
      "isUpdate": 0
    }
  ],
  "searchCount": true,
  "size": 1,
  "total": 43
}
```


## 待学习

- [springboot-guide](https://github.com/CodingDocs/springboot-guide)
- [一套全面又有实际意义的axios封装+api管理方案](https://github.com/slevin57/Blog/issues/11)
