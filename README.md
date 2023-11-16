[![Docker Image CI](https://github.com/Ligandlly/demo-preprocess/actions/workflows/docker-image.yml/badge.svg)](https://github.com/Ligandlly/demo-preprocess/actions/workflows/docker-image.yml)

# 后端API

## 查询

* Url: http://122.9.154.144:6001/queryDatabase
* Method: POST
* Body:

```json
{
    "question": "杨梓的同学有哪些？"
}
```

## 抽取

* Url: http://122.9.154.144:6001/getExtractionResult
* Method: POST
* Body:

```json
{
    "method": "SeqGPT",
    "task": "IE",
    "params": {
        "PaddleUIENER_type": 0,
        "relation_type": "attribute"
    },
    "text": {
        "title": "和刘洋一起去吃东坡肉",
        "time": "",
        "content": "",
        "classification": ""
    }
}
```

> 抽取的格式和来源的app有关，详情如下


## 不同app的格式

### 公共部分

> 每个app不同的部分是structed_content

```json
{
    "text": {
        "user_id": "", 
        "appname": "Calendar/Photo/SMS/MyDevice/Clipboard/Contacts/Email/Memo/UserInput/GPS",
        "operation_type": "add/update/delete/none",
        "info_id": ".../base",
        // "unstructed_content": "",
        "structed_content": {} // 允许为空 
    },
    "method": ...
}
```

---

### Calendar

时间格式：yyyy-MM-dd HH:mm:ss

```json
{
    "title": "",
    "description": "",
    "location": "",
    "allDay": "", // "0" or "1", "1" -> 8:00 ~ 20:00
    "startTime": "",
    "endTime": "",
    "RRule": "", // 重复规则，待处理
    "RDate": "", // 具体重复时间，待处理
    "alertTime": "", // 提醒时间
    "isImportantAlert": true, // 待定
    "ownerAccount": "",
    "timezone": "",
    "hasAttendeeData": "",
    "attendeeEmail": "",
    "attendeeName": "",
    "attendeeStatus": ""
}
```

### Clipboard

```javascript
{
    "timestamp": "", // yyyy-MM-dd HH:mm:ss
    "content": ""
}
```

### Contacts

```javascript
{
    "name": "",
    "phoneNumber": "",
    "company": "",
    "address": "",
    "email": "",
    "note": "",
    "birthday": "",
    "group": "" // 待定
}
```

### MyDevice

``` json
{
    "AndroidID": "",
    "Brand": "",
    "Product": "",
    "OAID": "" // 待定
}
```

### Email

```json
{
    "subject": "",
    "time": "",  // 带日期
    "sender": "",
    "recipient": "",
    "ccAddress": "",
    "content": ""
}
```

### Memo

``` json
{
    "title": "",
    "time": "",
    "content": "",
    "classification": ""
}
```

### UserInput

```json
{
    "timestamp": "",
    "content": ""
}
```

### SMS

```json
{
    "type": 0,
    "phone number": "",
    "phone name": "", // 可能为空
    "contents": "",
    "time": ""
}
```

type:

``` java
MESSAGE_TYPE_ALL    = 0;
MESSAGE_TYPE_INBOX  = 1;
MESSAGE_TYPE_SENT   = 2;
MESSAGE_TYPE_DRAFT  = 3;
MESSAGE_TYPE_OUTBOX = 4;
MESSAGE_TYPE_FAILED = 5; // for failed outgoing messages
MESSAGE_TYPE_QUEUED = 6; // for messages to send later

```

### GPS

``` javascript
{
    "timestamp": "",
    "location": "" // 城市
}
```

### Photo

``` javascript
{
    "imageName": "", // 文件名
    "imageTime": "", // 可能为空
    "imageLocation": "", // 可能为空
    "classification": [
        {
            "result": "", // 标签
            "possibility": 0.1
        }
    ],
    "scene": [
        {
            "result": "", // 标签
            "possibility": 0.1
        }
    ],
    "cnIdCard": {
        "name": "",
        "sex": "",
        "nation": "",
        "birthday": "",
        "address": "",
        "idNum": ""
    },
    "ocr": {
        "result": ""
    }
}
```

## run.py

### `/getFormatResult`, POST

### 联系人

``` json
{
    "user_id": "",
    "appname": "Contacts",
    "operation_type": "add",
    "structured_content": {
        "name": "",
        "phoneNumber": "",
        "company": "",
        "address": "",
        "email": "",
        "note": "",
        "birthday": ""
    }
}
```

### 日历

```javascript
{
    "user_id": "",
    "appname": "Calendar",
    "operation_type": "add",
    "structured_content": {
        "title": "",
        "description": "",
        "location": "",
        "allDay": "", // "0" or "1", "1" -> 8:00 ~ 20:00
        "startTime": "",
        "endTime": "",
        "RRule": "", // 重复规则，待处理
        "RDate": "", // 具体重复时间，待处理
        "alertTime": "", // 提醒时间
        "isImportantAlert": true, // 待定
        "ownerAccount": "",
        "timezone": "",
        "hasAttendeeData": "",
        "attendeeEmail": "",
        "attendeeName": "",
        "attendeeStatus": ""
	}
}
```



