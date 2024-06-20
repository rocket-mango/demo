# 🥭 망하지망고
이화여자대학교 컴퓨터공학과 캡스톤디자인 과목에서 개발한 어플 '망하지망고' 입니다. Spring Boot와 React Native를 활용하여 망고 잎 사진으로 망고 질병을 식별하는 안드로이드 어플리케이션을 구현하였습니다.


# 동작 환경
+ AWS EC2 : Ubuntu 22.04 LTS AWS EC2
+ 프레임워크 : Spring Boot v3.1.4
+ 데이터베이스 : MySQL 8.0.33

## 백엔드 배포 API
'''
https://api.capston-test-mm.p-e.kr
'''
배포 서버 URL입니다. 아래 서버주소로 테스트하실 수 있습니다.

```
http://localhost:8080
```


---


# 로컬에서 서버 실행하고 테스트하는 방법

### Requirements
+ AWS EC2 : Ubuntu 22.04 LTS AWS EC2
+ 프레임워크 : Spring Boot v3.1.4
+ 데이터베이스 : MySQL 8.0.33
+ Redis 3.0.504
+ intelliJ 설치 필수!

### Install
로컬에서(테스트용, 배포x) 테스트


1. 저장소 클론
   
```
git clone https://github.com/rocket-mango/mango_BE.git
```

2. MySQL 데이터베이스를 설치하고 'mangodb' 데이터베이스 스키마를 생성합니다.

![image](https://github.com/rocket-mango/mango_BE/assets/104640725/ad5f175e-8acf-4676-b0e5-621381dca476)

이후 FarmingInfoCategory, FarmingInfo, Disease의 내용을 db에 저장합니다.

+ FarmingInfocategory 쿼리

```
INSERT INTO FarmingInfoCategory (category_name)
VALUES 
('망고 상세 Tip'),
('망고 성장 Tip'),
('망고 재배 Tip'),
('망고 질병 관리 Tip');
```

+ FarmingInfo 쿼리

```
INSERT INTO FarmingInfo (title_1, summary, ref_image_url, content_1, image_url_1, title_2, content_2, image_url_2, topic, youtube_url, fcid)
VALUES 
('저온에서 결실하기 쉬운 품종',
'우리나라에서 재배하기 좋은 망고 품종이 궁금하다면?\n다양한 특성을 가진 여러 품종들을 알아보세요!',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/image_tips_1_1.png',
'망고는 성숙기가 되었을 때, 과실이 낙과하는 품종과 낙과하지 않는 품종이 있다. 대부분의 망고 품종은 착색되어 낙과하기 전에 과실 내부가 성숙되면 낙과 전 수확하여 실온에서 후숙시키고 부드러워졌을 때에 먹는 것이 일반적이다. 그러나 품종에 따라서도 완숙하여 수확 후 먹거나, 수확 후 후숙하여 먹는 것 등 다양한 특성을 나타내므로 수확 적기의 판정은 품종마다도 다르다. 예를 들어 만생종 망고인 케이트(Keitt) 품종은 숙기가 되어도 과피는 녹색 그대로이기 때문에 수확 적기의 판단에는 생산자의 많은 경험이 요구된다.수확이 늦어지면 과실 내부에서 종자가 발아하고 과육에 쓴맛을 일으키는 경우가 있다. 반면 너무 일찍 수확할 경우 후숙 중 과실이 시들거나 당도가 너무 낮아 품질이 저하될 수 있다. 어윈 품종과 같이 성숙기에 낙과하는 품종이 생산자 입장에서는 수확 적기의 판단이 쉬워 편리하며, 예상 수확량의 파악이 가능하고 판매 계획을 세우기 쉬운 장점이 있어 경험이 없는 사람이라도 가능하다. 어윈 품종과 같이 완숙하여 자연낙과하는 품종에는 라포자(Rapoza), 토르베트(Torbet) 품종이 있다.', 
NULL, 
'완숙해서 낙과하는 품종과 낙과하지 않는 품종',
'망고는 성숙기가 되었을 때, 과실이 낙과하는 품종과 낙과하지 않는 품종이 있다. 대부분의 망고 품종은 착색되어 낙과하기 전에 과실 내부가 성숙되면 낙과 전 수확하여 실온에서 후숙시키고 부드러워졌을 때에 먹는 것이 일반적이다. 그러나 품종에 따라서도 완숙하여 수확 후 먹거나, 수확 후 후숙하여 먹는 것 등 다양한 특성을 나타내므로 수확 적기의 판정은 품종마다도 다르다. 예를 들어 만생종 망고인 케이트(Keitt) 품종은 숙기가 되어도 과피는 녹색 그대로이기 때문에 수확 적기의 판단에는 생산자의 많은 경험이 요구된다.수확이 늦어지면 과실 내부에서 종자가 발아하고 과육에 쓴맛을 일으키는 경우가 있다. 반면 너무 일찍 수확할 경우 후숙 중 과실이 시들거나 당도가 너무 낮아 품질이 저하될 수 있다. 어윈 품종과 같이 성숙기에 낙과하는 품종이 생산자 입장에서는 수확 적기의 판단이 쉬워 편리하며, 예상 수확량의 파악이 가능하고 판매 계획을 세우기 쉬운 장점이 있어 경험이 없는 사람이라도 가능하다. 어윈 품종과 같이 완숙하여 자연낙과하는 품종에는 라포자(Rapoza), 토르베트(Torbet) 품종이 있다. ', 
NULL,
'망고 품종 선택 Tip', 
NULL, 
(SELECT fcid FROM FarmingInfoCategory WHERE category_name = '망고 상세 Tip'));

INSERT INTO FarmingInfo (title_1, summary, ref_image_url, content_1, image_url_1, title_2, content_2, image_url_2, topic, youtube_url, fcid)
VALUES 
('알폰소(Alphonso, 인도)',
'인도, 중유럽에서 인기가 많은 망고는 무엇일까요?\n알폰소와 아멜리를 소개합니다!',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/image_tips_1_2.png',
'알폰소 망고는 ‘Badami’, ‘Gundu’, ‘Haphus’, ‘Kagdi’, ‘Khader’ 등으로 알려져 있다. 나무의 수세는 중간, 수형은 장원형인 중만생종이다. 과피색은 노란색, 과형은 난형, 종경은 평균 6cm, 횡경은 5cm, 과중은 225~325g (평균 226g), 과피는 얇다. 과육은 노란빛을 띠며 단단~연하며 섬유질이 적고 달콤한 풍미를 지닌다. ‘King of Mango’라고 불리며 인도와 국제 시장에서 고가로 판매된다. 종자는 크고, 단배성이다. 인도에서는 다수확 품종이다.',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/tip_1_2_1.png', 
'아멜리(Amelie, 서아프리카)',
'아멜리 망고는 서아프리카 캐리비안에서는 ‘Gouvemeur’로 알려져 있다. 수고가 크고 원형이며, 밀집한 수관을 갖고 있으며 조생종이다. 과실 종경은 10~15cm, 횡경 10cm, 과중은 300~600g(평균 360g), 과피는 두껍고 분리가 어렵다. 과육은 연하고 즙이 많고, 진한 오렌지색으로 달콤하고 진하며 섬유질은 없는 편이다. 종자는 단배성이며, 중간 크기이다. 해당 품종은 켄트(Kent)와 함께 부르키나파소, 아이보리코스트, 말리에서 프랑스로 수출되는 등 중유럽국가에서 인기가 꾸준히 있는 품종이다. ',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/tip_1_2_2.png',
'망고 품종 특성 01',
NULL, 
(SELECT fcid FROM FarmingInfoCategory WHERE category_name = '망고 상세 Tip'));

INSERT INTO FarmingInfo (title_1, summary, ref_image_url, content_1, image_url_1, title_2, content_2, image_url_2, topic, youtube_url, fcid)
VALUES 
('아루마니스(Arumanis, 인도네시아)',
'접목번식이 쉬운 아르마니스, 다수확이 가능한 품종 반가나팔리에 대해 알아보세요!',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/image_tips_1_3.png',
'Haru manis로 불린다. 수세가 강하고 수고가 높고 약간 개방형 수관이며 중생종에 속한다. 과실은 크고 밝은 노란색 반점이 있는 녹황색이다. 과실 종경은 11~14cm, 횡경은 4.75~6.5cm, 과중은 200~350g 이다. 과피는 얇고 거칠고 쉽게 분리되는 편이다. 과육은 단단하고, 섬유질이 약간 있으며 다즙성이다. 과육색은 연노란색, 달콤하고 강한 향을 내며 맛이 없는 편이다. 종자는 단배성이며 종피가 두껍다. 나무가 잘 생존하기 때문에 상대적으로 접목번식이 쉬운 품종에 속한다',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/tip_1_3_1.png', 
'반가나팔리(Banganapali, 인도)',
'‘Beneshan’, ‘Chappatai’로도 불린다. 수세는 중간 정도이고, 수관이 둥근 중생종이다. 과피색은 담황색~노란색을 띠며, 과실 모양은 난형이다. 과실은 크고 과피는 부드럽고 얇으며 광택이 난다. 과육은 단단하고 섬유질은 없으며 과즙이 많다. 과육색은 연노란색이고 풍미가 좋다. 종자는 단배성으로 다수확성 품종이다. ',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/tip_1_3_2.png',
'망고 품종 특성 02', 
NULL, 
(SELECT fcid FROM FarmingInfoCategory WHERE category_name = '망고 상세 Tip'));

INSERT INTO FarmingInfo (title_1, summary, ref_image_url, content_1, image_url_1, title_2, content_2, image_url_2, topic, youtube_url, fcid)
VALUES 
('단배성 종자',
'망고를 번식시키기 위해 ‘단배성’ 망고와 ‘다배성’ 망고를 접목시킵니다.\n \‘단배성\’과 \‘다배성\’은 무엇일까요?',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/image_tips_2_1.png',
'단배성종자는 한 개의 생식 배만을 갖기 때문에 한 개의 단배성 품종의 종자에서는 한 개의 실생묘만 자란다. 망고에 대한 영양 번식법이 알려지기 전까지는 널리 이용되었으며 중세 인도에서 대규모 실생 망고 과수원이 조성되었고, 이후 15세기 포르투갈인들에 의해 영양 번식법이 인도로 도입되기 전까지 널리 이용되었다. ',
NULL, 
'다배성 종자',
'다배성 종자는 열대 우림 지역에서 자라는 망고 품종에서 일반적으로 발견된다. 주심배 실생에서 나온 나무는 모본과 동일하다. 다배성 종자에서 오직 한 개의 배만이 접합자(zygote)로부터 온 것이며, 접합배는 대개 퇴화하거나 약하고, 발육이 더딘 실생묘가 만들어진다. 한 개의 다배성 종자에서 대략 3~8개의 실생묘가 관찰되며, 30개 혹은 그 이상 보고된 바가 있다. 상업적 재배에서 주심배 실생과 접합배 실생의 구분은 발아 후 1개월 정도 후 수세 차이에 의해 가능하다. 동남아시아와 라틴 아메리카의 열대 지역에서는 전통적으로 다배성 품종을 종자번식하여 왔는데, 많은 망고 재배 지역에서는 다배성과 단배성 선발체를 균일한 주심배 실생에 접목하고 있다.',
NULL,
'망고의 번식 01: 종자 번식', 
NULL, 
(SELECT fcid FROM FarmingInfoCategory WHERE category_name = '망고 성장 Tip'));

INSERT INTO FarmingInfo (title_1, summary, ref_image_url, content_1, image_url_1, title_2, content_2, image_url_2, topic, youtube_url, fcid)
VALUES 
('대목용 종자 준비',
'망고 재배 전문가는 씨앗부터 다르게 준비한다! \n\‘대목용 종자\’에 대해 알아보고 파종 방법까지 알아보아요!',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/image_tips_2_2.png',
'준비대목은 항상 실생묘를 이용한다. 대목은 대부분 특정한 토양에 적합하고 스트레스에 저항성을 갖도록 선발되었다. 접목된 품종의 특성은 상당 부분 예측이 가능하다. 인도와 멕시코에서는 일반적으로 주심배 실생묘를 대목으로 사용한다. 성숙한 열매에서 채취한 신선한 망고 종자의 발아율은 약 76~91% 정도로, 너무 익거나 단단하거나 혹은 덜 익은 열매에서 채취한 종자보다 높다. 발아율 및 실생묘의 수세는 종자의 무게와 관련이 있고, 자엽이 큰 종자가 작은 종자보다 일찍 발아하고 저장력이 좋으며 수세가 좋다. 망고 종자는 저장성이 약하기 때문에, 수집된 종자는 일주일 이내에 파종하여야 한다. 망고 종자의 최대 저장 기간은 30일 정도이며, 습한 환경에서는 곰팡이가 생겨 썩기 쉽기 때문에 채취 후 습하지 않은 곳에서 잘 보관하여 한달 이전에 파종하면 80% 정도 발아가 이루어진다.',
NULL, 
'대목용 종자 파종',
'파종대목용 종자 발아를 위한 표준 실행법은 다양하다. 인도에서는 파종상이 이용되고, 다른 나라에서는 화분이나 비닐 주머니에 한 개씩 심는다. 파종상과 묘상은 식물체와 토양의 수분이 증발되지 않도록 그늘지게 하며 서리, 우박, 폭풍우로부터 보호될 수 있도록 한다. 차광은 건조하고 더운 지역에서 특히 유용하다. 모래가 많은 사질토양이나 점질토양은 일반적으로 망고 종자의 발아에 적합하지 않다. 토양은 배수가 잘 되어야 하고, 유기물이 풍부해야 한다. 망고 종자는 흙이 25cm 정도 있는 파종상에 파종하며, 파종상의 바닥은 콘크리트나 철판, 플라스틱판으로 단단하게 만들어 발아된 실생묘 뿌리의 잔뿌리가 잘 발달할 수 있도록 한다. 망고 종자를 20~100ppm GA3 용액에 24~48시간 담그거나, 50~300ppm GA3를 살포하면 발아가 촉진되나 이렇게 생산된 실생묘는 접목에는 부적합하다. 망고 종자는 5~7cm 깊이로 볼록하게 나오게 심거나 종자 일부가 노출되게 심으며, 파종 후, 관수하여 파종상을 촉촉하게 유지해준다. 파종 후 종자는 2~3주 내로 발아한다. 발아된 잎이 황갈색을 띠며 내종피 껍질이 남아 있는 30일 정도 자란 실생묘는 조금 더 기간이 지난 식물체보다 이식 후 생존율이 높다. 유년기의 실생묘는 이식 시 뿌리의 손상이 덜하다',
NULL,
'망고의 번식 02: 영양 번식',
NULL, 
(SELECT fcid FROM FarmingInfoCategory WHERE category_name = '망고 성장 Tip'));

INSERT INTO FarmingInfo (title_1,summary, ref_image_url, content_1, image_url_1, title_2, content_2, image_url_2, topic, youtube_url, fcid)
VALUES 
('호접',
'호접? 허접? \n망고 접목, 여기서 시작해보세요!',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/image_tips_2_3.png',
'호접은 모수에 붙어 있는 상태의 접수를 실생 대목에 접목하는 방법이다. 접수와 대목의 한쪽을 5~7cm 정도로 비슷한 깊이로 잘라내어 접수와 대목의 형성층을 맞추어 접목한다. 접수와 대목의 절단면을 맞춰 누르고 왁스가 발라진 끈 혹은 접목테이프, 파라필름 등으로 단단하게 묶는다. 연결된 후에는 접수의 접목 부위 밑부분을 절단하고, 대목은 접목 부위 위쪽을 절단한다. 아열대 지역에서 많은 품종들이 봄에 호접하면 약 88~100%의 성공률을 보인다. 봄에 접목하면 실생묘의 연령 및 크기에 영향을 크게 받지 않는다. 여름에 접목하는 경우에는 큰 실생묘를 이용한 접목이 성공률이 높다. 또한 열대 지역에서는 일 년 내내 호접이 가능하나, 아열대 지역에서는 겨울에 식물이 휴면 상태에 있을 때 접목하는 것은 피해야 한다. 호접은 나무가 활발히 성장하고 수액이 흐를 때 진행하여야 한다. 따라서 강우량이 적은 지역에서는 비가 오기 시작하여야 접목을 실시하고, 강수량이 많은 지역에서는 우기가 끝난 후 접목을 실시하여야 한다. 접목 시 기온은 15℃ 이하로 내려가서는 안 되며, 건강하고 품종이 확실한 것을 접수로 사용해야 한다. ',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/tip_2_3_1.png', 
'혀접',
'혀접은 접수보다 굵은 대목을 이용한다. 대목을 호접과 같이 절단하고 줄기 내부로 1차, 절단면의 절반 정도 길이로 2차 절단을 실시하여 혀 모양이 되게 한다. 접수도 비슷한 방법으로 절단한다. 이렇게 만들어진 두 개의 절단면은 서로 들어맞는데, 절단면을 맞추어 접목테이프 혹은 파라필름으로 단단히 고정한다. 활착이 이루어지고 접목 1~2개월 후 접수를 모수로부터 절단하고 대목의 윗부분을 접목부로부터 제거한다. 혀접은 호접보다 다소 복잡하긴 하나 접목부에 세 개의 형성층이 있어 성공률은 호접에 비해 높다. 호접의 성공률이 평균 73%인 데에 반해 혀접의 성공률은 약 84% 정도이다.',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/tip_2_3_2.png', 
'망고의 영양 번식 방법', 
NULL, 
(SELECT fcid FROM FarmingInfoCategory WHERE category_name = '망고 성장 Tip'));

INSERT INTO FarmingInfo (title_1, summary, ref_image_url, content_1, image_url_1, title_2, content_2, image_url_2, topic, youtube_url, fcid)
VALUES 
('겨울철 일조량이 충분한 지역',
'한국에서 열대과일을 키우려면?\n이 2가지를 고려하세요!',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/image_tips_3_1.png',
'망고는 본래 열대 지역에서 자라나는 호광성 과수이다. 큰 과실을 생산하기 위해 과실비대기에 관수하는 것도 중요하지만, 당도가 높은 과실을 생산하기 위해서는 광합성이 충분히 이루어져야 하며 광합성에는 햇빛이 중요하다. 특히 어윈 등의 적색계 망고는 어린 과실에서 햇빛을 많이 받아야 안토시아닌 합성이 원활히 이루어져 착색이 잘 되고, 당도가 높아진다. 화아분화에 있어서도 가을철부터 겨울철에 걸쳐 적절한 일조가 필요하고, 화아분화 이전에도 충분한 일조가 주어져야 결과모지가 충분히 저장양분을 축적해 충실한 화서가 형성될 수 있다. 충분히 양분을 축적한 가지에서 발생한 화서는 크기가 크고 꽃 수도 많으며, 충실한 양성화 비율이 높아 결실률이 높다. ',
NULL, 
'물이 충분히 확보될 수 있는 지역',
'노지재배에서는 큰 문제가 되지 않지만 하우스 재배 시에는 매주 정량의 관수를 실시해야 한다. 관수량은 토질에 따라 다른데, 1일 소비하는 물량을 5mm로 하면 6일 동안에는 30mm이므로 6일마다 30톤/10a의 관수용 물이 필요하다. 따라서 시설은 지하수, 농업용수 등 물을 공급받기 쉬운 장소에 마련하거나, 어려울 경우 빗물을 탱크에 저장하여 공급하는 등 여러 방면에서 수분 확보 방법을 고려하여야 한다.',
NULL,
'재배 적지 선택 방법 01', 
NULL, 
(SELECT fcid FROM FarmingInfoCategory WHERE category_name = '망고 재배 Tip'));

INSERT INTO FarmingInfo (title_1,summary, ref_image_url, content_1, image_url_1, title_2, content_2, image_url_2, topic, youtube_url, fcid)
VALUES 
('토심이 너무 깊지 않은 토양',
'망고 재배의 핵심?\n올바른 토양을 선택하는 방법을 알아보세요!',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/image_tips_3_2.png',
'망고의 뿌리는 직근성으로, 뿌리가 토양 깊이 진입하여 수세의 컨트롤이 어렵다. 토심이 깊은 경우 뿌리에서 양수분흡수가 활발히 이루어져 영양생장이 계속되어 꽃이 피지 않는 등 악순환이 지속된다. 일본의 망고 과원에서는 암반 위 50cm 깊이에 객토하여 재배하는 경우가 있다. 이렇게 하면 물의 배수는 원활히 이루어지고 직근이 낮게 뻗어 수세의 조절이 용이하고 개화가 잘 이루어진다. 따라서 깊이 80cm, 폭60cm로 구덩이를 파 방근(防根)시트를 깔고 그 위에 유기물이 혼합된 상토를 넣어 재배하면 배수도 잘 이루어지고 수세를 조절할 수 있다.',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/tip_3_2_1.png', 
'pH 6.6의 약산성 토양',
'망고 재배에 알맞은 토양의 산도는 pH 6.5~7.0 정도로 약산성이다. 강산성 토양에서는 과실의 착색이 나쁘고 pH 7.5 이상의 알칼리성 토양에서는 생육이 떨어진다. 석회(Ca) 함량이 많은 알칼리성 토양에서는 철(Fe), 아연(Zn)과 같은 미량요소의 흡수가 억제되어 생육 불량을 일으키며, 반대로 산성 토양에서 Ca가 부족한 경우에는 과육이 붕괴된 과실의 발생이 많아진다. 생육을 원활히 하기 위해서는 공급되는 물의 pH를 조절하여야 하며, 결핍된 미량요소를 시비해줌으로써 미량요소 결핍 문제를 해결해야 한다. ',
NULL,
'재배 적지 선택 방법 02', 
NULL, 
(SELECT fcid FROM FarmingInfoCategory WHERE category_name = '망고 재배 Tip'));

INSERT INTO FarmingInfo (title_1,summary, ref_image_url, content_1, image_url_1, title_2, content_2, image_url_2, topic, youtube_url, fcid)
VALUES 
('개화 유도(Flower induction)',
'망고 재배자들이라면, \n반드시 알아야 하는 재배 기온 조절!',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/image_tips_3_3.png',
'꽃의 수는 많으나 화기의 수가 적고 낙과가 잘 일어나기 때문에 망고는 다른 과수류에 비해 단위면적당 수확량이 적다. 일반적으로 유전적 혹은 환경적 요소, 식물체 내부적 요인에 의해 식물의 개화가 유도되는데 아열대 지역의 망고는 온도가 개화를 촉진하는 중요한 요소로 작용한다. 기온이 낮을수록 개화가 잘 이루어지는데 헤이든(Haden) 품종의 경우 주야 온도가 23/19℃, 25/19℃일 때, 개화율이 각각 87%, 60%였으며 31/25℃에서는 개화가 일어나지 않고 영양생장만 이루어진다. 다른 품종에서의 연구에서도 주야 온도 15/10℃의 상대적으로 저온 조건에서 개화가 유도되는 결과를 보였다. 영양생장이 억제되고 개화가 유도되는 기온인 15/10℃에서도 기온이 25℃ 이상이 되면 개화가 억제되는 경향을 보인다. 이는 뿌리로부터 신호전달이 이루어져 개화유도에 영향을 미치기 때문인데, 지온이 높아지면 뿌리로부터 지상부로의 개화 유도 물질의 이동이 어려워져 개화가 억제된다. ',
NULL, 
'화기의 발육과 수분(Flower Organ Development and Pollination)',
'화기의 생장과 발육은 지상부 최저 한계온도인 15℃ 이하에서도 지속된다. 화기 생장에 영향을 미치는 최저온도는 12.5℃로 보고되어 있다. 온도에 따라 웅성화와 양성화 비율이 결정된다. 온도가 높아질수록 개화가 늦어지고 양성화 발생률이 높아진다.켄싱톤(Kensington) 품종은 일 평균 기온 15℃ 이하에서 저온 피해를 입는다. 15℃ 이하의 온도에서는 암술대(Stigma)의 길이가 60% 정도 감소하며, 착과율이 현저히 낮아진다. 비정상적 화기는 씨방이 작아지며 배주와 암술대의 색이 짙어지고, 꽃밥(anther)이 적색에서 검은색으로 변하는 특징을 보인다. 한편 화분의 활력 또한 저온에 의해 낮아지는데, 수술이 성숙되는 기간 동안 야간온도 4.4℃ 이하의 저온에 노출되면 화분의 활력이 50% 이하로 크게 저하된다. ',
NULL,
'망고 재배 기온',
NULL, 
(SELECT fcid FROM FarmingInfoCategory WHERE category_name = '망고 재배 Tip'));

INSERT INTO FarmingInfo (title_1,summary, ref_image_url, content_1, image_url_1, title_2, content_2, image_url_2, topic, youtube_url, fcid)
VALUES 
('진균(곰팡이, mold)',
'망고 재배, OO을 조심하세요!\n가장 많이 발생하는 망고 질병은?',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/image_tips_4_1.png',
'진균은 영양기관과 생식기관을 가지고 있다. 영양기관은 실모양의 균사체로 되어 있고, 균사체의 생육이 어느 단계에 이루면 번식을 위해 포자라고 부르는 생식기관이 형성되어 대량으로 증식하고 퍼져나가 식물에 피해를 입히게 된다. 진균류는 과수에 병을 일으키는 병원균 중 가장 많은 수를 차지한다.',
NULL, 
'세균(Bacteria)',
'세균은 진균과는 달리 단세포 미생물로서 그 형태가 단순한 것이 특징인데, 기본형으로는 짧은 막대기 모양의 간균, 공 모양의 구균, 나사처럼 꼬인 나선균, 구부러진 모양의 콤마균 및 사상형 균이 있다. 그러나 식물 병원성 세균의 모양은 대다수가 간상이며, 크기는 길이 0.6~3.5µ, 직경 0.3~1.0µ 범위 내에 있다. 세균의 증식은 단순 분열에 의해 이루어지는데, 증가하는 속도가 다른 어떠한 미생물보다도 빨라 짧은 시간 내에 막대한 숫자에 이르게 된다. ',
NULL,
'병원균의 종류 01', 
NULL, 
(SELECT fcid FROM FarmingInfoCategory WHERE category_name = '망고 질병 관리 Tip'));

INSERT INTO FarmingInfo (title_1, summary, ref_image_url, content_1, image_url_1, title_2, content_2, image_url_2, topic, youtube_url, fcid)
VALUES 
('바이러스(Virus)',
'잘 자라던 망고 잎에 반점이 나타나는 이유?\n바이러스를 의심해보세요!',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/image_tips_4_2.png',
'바이러스는 핵산과 단백질로 이루어진 구조를 갖는다. 바이러스 입자는 전자현미경으로나 식별이 가능할 만큼 작아 일반적으로 기주(host) 식물에 나타나는 병의 증상을 통해 그 특성을 구별할 수 있다. 잎에 반점이 나타나거나 모양이 변형되고, 과실이 작아지거나 표면의 얼룩, 당도 저하 등이 바이러스에 의한 병의 일반적인 증상이다. 가지에서의 증상으로는 표피조직이 부분적으로 괴사하거나 기형이 되는 경우가 있다. 이러한 증상들이 심화되면 나무가 괴사되기도 한다. 그러나 어떤 종류의 바이러스는 기주에 아무런 증상도 나타내지 않고 잠복하는 경우가 있는데 이러한 바이러스를 잠복성 바이러스(Latent virus)라고 한다. ',
NULL, 
'바이로이드(Viroid)',
'바이로이드는 감자 걀쭉병의 병원체(PSTV, Potato Spindle Tuber Viroid)를 정제하여 그 특성을 조사하는 과정에서 알려지게 되었다. 이 병은 오랫동안 바이러스가 원인이라고 믿어왔는데 1967년 다이너(Diener)와 래이머(Raymer)가 이 병의 병원 인자가 바이러스와는 다르게 핵산(RNA)으로만 구성되어 있고 감염 조직에 바이러스 입자가 존재하지 않아 바이러스와는 다르다는 것을 밝히며 1971년 바이로이드라는 용어를 널리 사용하게 되었다.',
NULL,
'병원균의 종류 02', 
NULL, 
(SELECT fcid FROM FarmingInfoCategory WHERE category_name = '망고 질병 관리 Tip'));

INSERT INTO FarmingInfo (title_1, summary, ref_image_url, content_1, image_url_1, title_2, content_2, image_url_2, topic, youtube_url, fcid)
VALUES 
('월동기 방제',
'건강한 망고를 키우는 2가지 방법!',
'https://mango-s3-1.s3.ap-northeast-2.amazonaws.com/image_tips_4_3.png',
'과수는 영년생 작물(perennial crop)로 월동기 방제의 개념이 필요하다. 이는 전년도에 발생하여 나무줄기, 수피, 낙엽 등에서 월동한 병원균을 제거하거나 초기 밀도를 낮추는 것으로서, 생육기의 방제가 수월해지며 병원균의 활동 시작 전 방제가 가능하고, 약제가 잘 도달하여 방제 효과가 높다는 장점이 있다. 주로 적용되는 방법에는 월동처가 되는 전년도 낙엽 등의 부산물을 제거하고 거친 껍질을 벗겨주는 방법, 그리고 석회유황합제를 살포하여 광범위한 보호효과를 꾀하는 방법이 있다.',
NULL, 
'병 발생 확률을 낮추는 재배관리',
'과원 내 수관이 지나치게 복잡하면 통풍과 태양광의 투과가 잘 이루어지지 않아 수관 내부 온도가 상승한다. 강우 후 빗물이 쉽게 마르지 않게 되어 습도가 높게 유지되고, 병이 쉽게 발생하는 환경이 조성된다. 망고와 같이 시설하우스 내에서 재배하는 과수의 경우에는 환기 불량의 경우가 이에 해당한다. 병 발생은 온도나 잎, 줄기 표면에 수분(물기)이 존재하는 시간과 높은 상관성이 있으므로 바람과 햇볕이 잘 통하도록 관리하여 물기가 빨리 마르도록 관리하면 병 발생을 낮출 수 있다. 한편 토양이 과습한 경우 나무가 연약하게 자라고 병에 대한 내성이 약해진다. 특히 역병과 같은 토양 전염성 병은 배수가 불량한 토양에서 많이 발생하므로 배수 관리를 철저히 하도록 한다. 또한 질소 시비가 과다한 경우 나무가 웃자라거나 연약해져 병에 약해지므로 과다한 유기물 시용도 피해야 하며, 특히 완전히 부숙되지 않은 유기물의 시용을 주의해야 한다.'
NULL,
'과수 병 방제 기본 요령', 
NULL, 
(SELECT fcid FROM FarmingInfoCategory WHERE category_name = '망고 질병 관리 Tip'));

```


+ Disease 쿼리

```
insert into disease
values (
	1
	, '그을음 곰팡이를 제어하는 가장 좋은 방법은 그들의 당분 공급원을 제거하는 예방적 방법을 사용하는 것입니다. 잎에 있는 흡즙성 곤충과 그들을 돌보고 보호하는 개미를 제어하는 것도 중요합니다. 그을음 곰팡이가 관찰되는 즉시 곤충 방제를 시작해야 합니다. 살충제의 종류는 식물과 대상 해충에 따라 다릅니다. 일반적인 살균제는 곰팡이를 죽이는 데 효과적일 수 있지만 검은색을 제거하지는 못합니다. 장벽이나 살충제 미끼를 사용하여 개미를 제어하는 것도 또 다른 제어 방법입니다. 감염된 식물 부위의 대부분을 제거하기 위해 가지치기를 하는 것도 도움이 됩니다. 나무가 작은 경우, 그을음 곰팡이는 물이나 비누와 물을 강하게 분사하여 씻어낼 수 있습니다.'
	, '그을음병'
	, 'Sooty mould'
	, '망고 그을음병은 밀랍, 녹색 및 목화 쿠션 깍지벌레와 같은 수액을 빨아먹는 곤충, 가루이, 진딧물, 나무좀 등과 공생하지 않는 곰팡이 간의 상호작용으로 인해 분비되는 감로에 서식하는 곰팡이 종 중 하나입니다. 이 곰팡이는 나무에 약간의 미관상 피해만 줄 뿐 식물 조직을 감염시키지는 않습니다. 그러나 잎 엽록체에 도달하는 햇빛을 차단하여 식물의 광합성 및 성장에 부정적인 영향을 미칩니다.'
	, '기생충 곤충이 당분이 많은 감로를 먹이로 사용하면 그을음 곰팡이가 곤충이 잎에 남긴 당분 위에서 자라기 시작하여 잎 표면을 검은색으로 변하게 합니다. 이는 수액을 빨아먹는 곤충의 숙주가 되는 모든 식물이 그을음병에 걸릴 수 있다는 것을 의미합니다. 가스 교환 및 광합성 감소, 미관 손상, 시장성 감소, 주스 가공 시 과일 품질 감소 등의 문제가 발생합니다.');

insert into disease
values (
	2
	, '감염된 잎이나 부분은 가능한 한 빨리 제거해야 합니다. 또한 식물 주변을 잘 통풍되고 건조한 환경으로 유지해야 합니다. 식물에 충분한 일조량을 제공하고 잎이 너무 밀접하게 서로 접촉하지 않도록 합니다.'
	, '가루곰팡이'
	, 'Powdery Mildew'
	, '곰팡이균이 환경 조건에 의해 잎 표면에 번식합니다. 곰팡이 생장에 적합한 습도와 공기순환 부족이 주요 원인 중 하나입니다. 식물이 피해를 입은 벌레, 균류 또는 질병으로 인해 약해지면 곰팡이의 발생 가능성이 높아집니다.'
	, '잎의 표면에 흰색 또는 회색의 가루 모양의 곰팡이가 형성됩니다. 잎이 변색하거나 곰팡이에 의해 손상되어 잎이 떨어질 수 있습니다. 잎, 꽃, 귀의 줄기 및 과일의 성장을 저하시킵니다. 영향을 받은 과일은 크기가 커지지 않고, 완전히 성장하기 전에 떨어질 수 있습니다. 뿐만 아니라 공기 중에 퍼져서 이차적으로 전파됩니다.');

insert into disease
values (
	3
	, '현장 위생 및 방역 관리를 강화하고, 적절한 잡초 관리 (잡초는 해충의 중간 숙주 역할을 함) 및 감염된 잎의 가지치기가 필요합니다. 혼작 및 간작 농법을 통해 해충 개체 수를 감소시키고 과수원을 갈아엎어 번데기와 휴면 중인 유충을 태양열에 노출시켜 제거해야 합니다. 감염된 식물을 새로운 지역으로 이동시키지 않아야 하며, 주머니 나방을 손으로 제거하는 방법은 개체 수가 적을 경우 특히 효과적입니다.'
	, '주머니나방붙이'
	, 'Gall Midge'
	, '이 질병의 주 원인은 주머니나방붙이입니다. 주머니나방붙이는 나비목 주머니나방과의 곤충으로, 주로 열대 및 아열대 지역에서 발견됩니다. 망고 나무에 기생하여 잎에 작은 주머니 모양의 혹을 만들며, 이 혹은 주머니나방붙이의 알과 애벌레가 자라는 장소입니다.'
	, '주머니나방이 작물의 여러 성장 단계에서 피해를 줍니다. 유충(애벌레)은 잎 조직 내부를 뚫고 들어가 내부에서 먹이를 섭취하며, 이로 인해 잎에 작은 사마귀 모양의 혹이 형성됩니다. 혹 형성은 7일 이내에 시작되며 최대 지름은 약 3-4mm입니다. 혹이 많이 생긴 잎은 말려 올라가 조기에 떨어지고, 감수성 품종에서는 전체 가지의 고사를 유발할 수 있습니다. 나무에 남아 있는 혹이 생긴 잎은 탄저병의 접종원이 될 수 있는 것으로 알려져 있습니다. 번데기가 되기 위해 땅으로 이동할 때 유충이 혹을 떠나는 곳에서 작은 탈출 구멍이 발견될 수 있습니다. 이러한 구멍은 식물 조직으로 들어가는 진입점을 만들어 이차적인 곰팡이 감염을 유발합니다. 어린 과일이 공격을 받으면 탈출 구멍은 보통 꽃차례 축에 부착되는 지점 근처 과일의 아래쪽에 있습니다. 주머니나방이 많이 감염된 망고 나무는 꽃차례가 적게 발생하여 망고 열매의 수확량이 감소합니다.');

insert into disease (did, handle, name, name_en, reason, symptom)
values (
	4
	, '나무를 건강하게 유지하고 정기적으로 물을 주세요. 냉해 스트레스나 영양 결핍이 발생하기 쉬운 지역에 심지 마시고, 과수원을 정기적으로 모니터링하여 초기 단계에서 가능한 감염을 식별하세요. 또한 곰팡이의 주요 진입 부위인 나무에 손상이나 궤양이 생기지 않도록 주의하셔야 하며 과수원에서 죽은 나무 재료를 즉시 제거하세요. 균형 잡힌 시비 프로그램을 사용하는 것도 중요합니다.'
	, '일반 사멸'
	, 'Die Back'
	, '보트리오스페리아 로도이나(Botryosphaeria rhodina)는 식물의 괴사 조직에서 장기간 생존합니다. 이 균은 망고 나무의 줄기와 가지의 상처를 통해 혈관 시스템에 침입합니다. 감염의 정확한 메커니즘은 완전히 이해되지 않았습니다. 감염 경로는 곤충(딱정벌레)에 의한 상처 또는 현장 작업 중에 발생한 기계적 손상일 수 있습니다. 주요 감염원은 가지의 죽은 껍질에 있는 포자일 수 있습니다. 이들은 성장기에 나무에 남아있다가 수확기에 퍼집니다. 철분, 아연, 망간 결핍이 이 질병의 발생을 촉진할 수 있습니다. 수분 및 동결 스트레스도 이 질병과 관련이 있습니다. 이 질병은 연중 언제든지 발생할 수 있지만 후기 성장 단계에서 가장 뚜렷합니다.'
	, '망고나무에 보트리오스페리아 로도이나(Botryosphaeria rhodina)균이 감염되면 건조한 가지에서 증상이 나타나며, 완전히 낙엽이 질 수 있습니다. 질병의 첫 단계에서는 나무 껍질이 변색되고 더 어두워집니다. 다음 단계에서는 어린 가지가 밑에서부터 시들기 시작하여 잎까지 영향을 미칩니다. 엽맥이 갈색으로 변하면서 잎이 위로 말리고 결국 나무에서 떨어집니다. 고사의 마지막 단계에서는 가지와 가지가 고무를 분비합니다. 처음에는 작은 고무 방울이 눈에 띄지만, 질병이 진행됨에 따라 전체 가지나 줄기가 덮일 수 있습니다. 심한 경우 나무 껍질이나 전체 가지가 죽어 균열이 생길 수 있습니다.');

insert into disease (did, handle, name, name_en, reason, symptom)
values (
	5
	, '알을 많이 품은 잎을 잘라 모아서 건조하거나 태울 수 있습니다. 숙주식물 크라운 아래의 토양을 갈아엎으면 번데기를 죽일 수 있습니다. 화학적 방법으로는 어린 잎의 너비가 3cm일 때 살충제를 살포하는 방법이 있습니다. 주간 간격으로 2~3회 살포하면 Deporaus marginatus의 공격으로부터 어린 순을 효과적으로 보호할 수 있습니다. 트리클로폰, 델타메트린, 펜발레레이트 등의 살충제는 Deporaus marginatus의 방제에 효과적입니다.'
	, '절단 벌레'
	, 'Cutting Weevil'
	, 'Deporaus marginatus의 원인은 해당 식물에 대한 해충의 공격입니다. 이러한 해충은 주로 망고와 같은 열대 과일에 서식하며, 잎의 식물 조직을 먹어서 식물의 성장과 생산성에 해를 끼칩니다.'
	, 'Deporaus marginatus의 공격에 가장 눈에 띄는 증상은 잘리고 땅에 떨어진 어린 잎입니다. 감염된 식물에서는 벗겨진 가지를 쉽게 볼 수 있습니다. 성충의 섭식은 어린 잎에 \'창유리\'를 만듭니다.');

insert into disease (did, handle, name, name_en, reason, symptom)
values (
	6
	, '새로운 식물을 번식시킬 때는 무병주 근삽과 접수를 사용하세요. 종묘장 식물을 과수원에 도입하기 전에 BBS의 징후를 확인하세요. 만약 증상이 나타나면 해당 나무를 구매하지 말고, 무증상 나무로 대체하세요. 과수원에 도입할 모든 새 나무에 대해서는 도입 전에 구리 기반의 살충제(구리 기반 제품)를 뿌려주세요. 과일이 장기간에 걸쳐 높은 강수량에 노출되는 것을 피하기 위해 조생종 품종을 재배하세요. 즉, 가능한 한 우기를 피하세요. 바람에 의한 병원균 전파와 잎, 줄기, 과일에 대한 미소 손상을 줄여 병원균이 들어갈 수 있는 구멍을 만드는 바람막이를 설치하세요. 수관을 적시는(예: 대용량 오버헤드 시스템) 관개 시스템을 설치하지 말고, 지상부 나무 부분을 적시지 않는 저용량 관개 시스템(예: 드립 또는 마이크로 스프링클러)을 설치하세요. 꽃이 핀 후부터 수확기까지 구리 기반의 살충제를 사용하여 BBS 병원균의 밀도를 억제하세요. 우기에는 특히 중요합니다. 구리 기반 살충제는 2~4주 간격(라벨 지침에 따라)으로 살포하세요. 나무의 높이를 제한하고 열린 수관을 유지하기 위해 매년 나무를 전정하세요. 열린 수관은 공기 흐름과 빛 투과성을 개선하여 아침 이슬, 강우, 오버헤드 관개로 인한 잎, 과일, 줄기의 습윤 시간을 줄여줍니다. 전정 도구와 장비는 나무 사이를 소독하세요. 감염된 식물 부위는 제거하고 파괴하세요. 과일을 딸 때는 과일을 줄기에서 찢는 대신 과일 줄기를 잘라내어 줄기에 열린 상처를 내지 않도록 하세요.'
	, '세균 괴사병'
	, 'Bacterial Canker'
	, 'BBS의 원인균은 자연적인 구멍과 상처를 통해 줄기, 잎, 과일 조직을 감염시킵니다. 잎 병변은 엽맥을 따라 자주 발생하는 검은색 각진 반점으로 나타나며, 종종 유백색 테두리로 둘러싸여 있고(노란색) 헤일로가 있습니다. 병반은 큰 괴사성(죽은) 패치로 합쳐질 수 있으며, 고습 조건에서는 병반에서 세균이 흘러나올 수 있습니다. 감염 후 몇 개월이 지나면 잎 병변은 건조되어 밝은 갈색에서 재색으로 변합니다. 심한 경우 낙엽이 발생합니다.'
	, '과일의 성숙도가 증가함에 따라 과일의 민감도가 증가하며, 이는 엽맥(줄기와 과일에 있는 미세한 자연적인 구멍)의 약화와 관련이 있으며, 이로 인해 병원균이 정착할 수 있게 됩니다. 과일의 증상은 엽맥 주변의 불규칙한 작은 물에 젖은 점이나 작은 별 모양의 병변으로 시작됩니다. 질병이 진행됨에 따라 이러한 병변은 검게 변하며, 융기되고 합쳐지는 유백색 테두리가 나타나며, 그 다음 균열이 발생합니다. 이러한 괴사 반점은 각진 모양이고 융기되어 있으며, 탄저병 병변처럼 둥글고 과육 속으로 함몰되어 있지 않다는 점에 유의해야 합니다. 고습기에는 끈적끈적한 삼출물(세균 유출)이 흘러나올 수 있습니다. 탄저병과 마찬가지로 "눈물 자국" 감염 패턴도 발생할 수 있습니다. 심한 과일 감염은 과일 낙과를 초래합니다. 줄기의 증상으로는 세로 방향의 어두운 색 균열이 있으며, 이로 인해 줄기가 바람에 부러지기 쉬워집니다. 꽃과 열매 자루도 이 질병의 영향을 받을 수 있습니다.');

insert into disease(did, handle, name, name_en, reason, symptom)
values (
	7
	, '꽃이 피는 시기부터 만코제브(추천 라벨 속도로 14일마다)를 정기적으로 살포하면 과일의 감염 수준을 줄이는 데 유용합니다. 수확 14일 이내에는 만코제브를 사용하지 마세요. 녹색 미숙과에 탄저병이 심해지면 프로클로라즈를 몇 차례 신중하게 살포하는 것이 좋습니다. 그러나 프로클로라즈를 과다하게 사용하면 탄저병 내성균이 생길 수 있으므로 주의해야 합니다. 망고 스캐브 방제에 권장되는 구리 스프레이는 하루의 보류 기간만으로 탄저병을 방제할 수 있습니다. 망고 과일의 탄저병 방제를 위한 수확 후 처리제가 있습니다. 프로클로라즈는 냉비순환식 스프레이로 사용됩니다. 과일 파리를 방제하기 위해 사용되는 뜨거운 물에 담그는 방법도 탄저병과 줄기 끝 부패를 방제할 수 있습니다. 뜨거운 베노밀 담그기는 탄저병을 방제할 수 있으며, 줄기 끝 부패가 문제가 되는 경우에 유용합니다.'
	, '탄저병'
	, 'Anthracnose'
	, '망고 탄저병은 콜레토트리쿰 글로에오스포리oides 변종 미노(완전 단계의 이름인 글로메렐라 싱굴라타 변종 미노로도 알려져 있음)라는 곰팡이에 의해 발생합니다. 이 곰팡이의 포자 생산은 습하거나 습한 날씨에서 촉진됩니다. 이러한 포자의 분산은 특히 비와 바람에 의해 촉진됩니다. 이를 통해 비교적 짧은 거리에 걸쳐 질병이 확산될 수 있습니다.'
	, '잎 반점, 꽃 마름병, 시든 끝, 가지 마름병 및 과일 썩음병을 유발합니다. 잎과 가지에 작은 물집 같은 반점이 생깁니다. 어린 잎은 시들고 건조해집니다. 부드러운 가지는 시들고 죽는 증상이 나타납니다. 영향을 받은 가지는 결국 말라 버립니다. 과일에는 검은 반점이 나타납니다. 과일의 과육은 딱딱해지고, 익으면 갈라지고 썩습니다. 감염된 과일은 떨어집니다.');

```


3. 로컬 pc에 redis를 설치합니다.
<https://github.com/microsoftarchive/redis/releases> 주소에서
Redis-x64-3.0.504.msi 를 다운받은 후 설치합니다.

5. 프로젝트의 /src/main/resources경로에 application.yml 파일을 생성/작성합니다.

```
spring:
  servlet:
    multipart:
      maxFileSize: 10MB # 파일 하나의 최대 크기
      maxRequestSize: 30MB  # 한 번에 최대 업로드 가능 용량
  devtools:
    restart:
      enabled: true
  datasource:
    driver-classname: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mangodb?createDatabaseIfNotExist=true&characterEncoding=UTF-8&characterSetResults=UTF-8&serverTimezone=Asia/Seoul
    username: root
    password: [dbpassword]
  jwt:
    secret: alskdjfijopwijlakjsdfhiuawhkljnvuuuewkjhsssssssssssssssvvvvv
  jpa:
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
    generate-ddl: true
  security:
    oauth2:
      client:
        registration:
          naver:
            client-name: naver
            client-id: rWaf9unmPhRwh9b9zFOE
            client-secret: qJ25r0Gz8s
            redirect-uri: http://localhost:8080/login/oauth2/code/naver
            authorization-grant-type: authorization_code
            scope: name,email
        provider:
          naver:
            authorization-uri: https://nid.naver.com/oauth2.0/authorize
            token-uri: https://nid.naver.com/oauth2.0/token
            user-info-uri: https://openapi.naver.com/v1/nid/me
            user-name-attribute: response

  redis:
    host: localhost
    port: 6379

# AWS S3
cloud:
  aws:
    s3:
      bucket: mango-s3-1
    stack:
      auto: false
    region:
      static: ap-northeast-2
    credentials:
      access-key: [aws access key]
      secret-key: [aws secret key]
test: this is test

#이미지
imgTmpsave:
  location: C:\\Users\\user\\Downloads\\tmp

#ml public ip
mlserver:
  ip: http://43.134.38.62:8083

 ```

4. 어플리케이션을 실행합니다.
![image](https://github.com/rocket-mango/mango_BE/assets/104640725/d2575c64-0b26-4623-ae99-8fbb261215c3)

5. 회원가입 진행

/api/user/joinProc api 경로로 회원가입을 진행합니다.

```
{
  "name": "김망고",
  "nickname": "mangomango",
  "username": "yujin00",
  "password": "yujin0926$",
  "email": "mangorocket@ewhain.net"
}
```

6. postman에서 테스트합니다.

1) 로그인 후 header의 authorization에서 JWT를 복사합니다.
![image](https://github.com/rocket-mango/mango_BE/assets/104640725/4d2acc25-fbdf-45eb-bbed-600452dec2a3)

2) 이후 다른 api를 테스트할 때, header의 authorization에 복사한 jwt를 붙여넣고 테스트합니다.
/home 을 테스트합니다.

![image](https://github.com/rocket-mango/mango_BE/assets/104640725/fca9f963-2263-471c-ba7d-0fd8b309a33a)

다음과 같이 입력한 후 오른쪽 상단의 'Send' 버튼을 클릭합니다.

![image](https://github.com/rocket-mango/mango_BE/assets/104640725/a7d7f8fe-6e63-4fd0-9883-71bfdd6c79fb)

다음과 같은 결과를 받을 수 있습니다.


---
---



# 🧑‍🏫프로젝트 소개

### 프로젝트 제목
망하지망고
### 내용
망고 초보 재배자를 위해 이미지 객체 검출 및 분류 인공지능 모델을 기반으로 망고 잎 사진을 통해 망고 질병을 식별하고 재배 기초 정보들을 제공하는 모바일 어플리케이션 서비스입니다.
### 개발기간
2023.9 ~ 2024.6
### 팀원 소개
11팀 로켓단

FrontEnd
>  💚 박서연  🧡 최윤지

BackEnd
>  💙 정유진 💛 조하은


### 사용한 기술

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Nginx](https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![React Native](https://img.shields.io/badge/react_native-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)

![TensorFlow](https://img.shields.io/badge/TensorFlow-%23FF6F00.svg?style=for-the-badge&logo=TensorFlow&logoColor=white)
![NumPy](https://img.shields.io/badge/numpy-%23013243.svg?style=for-the-badge&logo=numpy&logoColor=white)
![Keras](https://img.shields.io/badge/Keras-%23D00000.svg?style=for-the-badge&logo=Keras&logoColor=white)


---


# 🏗 아키텍처 

![서비스 구조도 수정 (2) 1](https://github.com/rocket-mango/mango_BE/assets/104640725/a2e17e30-0fff-4078-b06b-0c36c8b64fbf)


---


# 🌹 주요 기능
1. 망고질병진단기능
![Frame 26085692](https://github.com/rocket-mango/mango_BE/assets/104640725/cf4b0c25-fa07-4f9c-a92a-3f2f99e3677c)

YOLOv8 및 ResNet50 인공지능 모델을 토대로 망고 잎 질병 분류가 가능한 커스텀 모델을 통해, 망고 잎 사진을 업로드하여 망고 질병 진단 및 증상, 원인, 해결 방법 등 결과를 확인할 수 있는 기능입니다.

3. 망고 팁 기능
![Frame 26085692 (1)](https://github.com/rocket-mango/mango_BE/assets/104640725/997adebb-024c-44b1-bc9b-e94c8e1dc62f)

재배, 품종, 성장, 질병관리 4가지 테마의 망고 재배와 관련된 정보를 제공하는 기능입니다.

5. 위치별 날씨 기능
![Frame 26085692 (2)](https://github.com/rocket-mango/mango_BE/assets/104640725/57e6cab5-7b57-41ee-95e7-5a91c721802f)

사용자 휴대폰 위도와 경도를 파악하여 위치기반 날씨, 기온 정보를 제공하는 기능입니다. 


---


# 사용한 OPEN SOURCE
+ [망고 잎 데이터셋](https://www.kaggle.com/datasets/aryashah2k/mango-leaf-disease-dataset)

 
# API 설명
 
#### 사용자
 
+ /api/home
- GET
- 입력 : 없음
- 내용 : 홈 화면에 띄울 정보를 보냄
- 출력 : 기상청 정보, 사용자의 마이망고리스트, 사용자 nickname

+ /api/user/joinProc
- POST
- 입력 : name, username, nickname, password, email
- 내용 : 사용자 회원가입
- 출력 : 사용자가 입력한 사용자 정보(name, username, nickname, password, email)

+ /api/user/resignation
- DELETE
- 입력 : 없음
- 내용 : 사용자 회원 탈퇴
- 출력 : 탈퇴한 사용자 닉네임(nickname)

+ /api/user/login
- POST
- 입력 : username, password
- 내용 : 사용자 로그인
- 출력 : Header의 authorization에 JWT

+ /api/user/logout
- GET / POST 
- 입력 : 없음
- 내용 : 사용자 로그아웃
- 출력 : 없음

+ /api/user/information
- GET
- 입력 : 없음
- 내용 : 사용자 정보를 필요로 할 때
- 출력 : 사용자 정보(name, username, nickname, email)

+ /api/user/modify
- PATCH
- 입력 : name, username, nickname, email
- 내용 : 사용자 정보 수정
- 출력 : 사용자가 수정한 사용자 정보(name, username, nickname, password, email)

#### 질병 검사

+ /api/disease/diagnosis
- POST
- 입력 : 망고 사진, location
- 내용 : 망고 검사 진행
- 출력 : top3 결과 리스트, 망고 결과 리턴

+ /api/disease/my-mango-list
- GET
- 입력 : 없음
- 내용 : 해당 사용자의 망고 검사 결과 리스트를 반환
- 출력 : 망고 결과 리스트

+ /api/disease/lists?location={location}
- GET
- 입력 : location
- 내용 : 사용자의 location에 해당하는 망고 결과 리스트만 리턴
- 출력 : 해당 location의 망고 결과 리스트

+ /api/disease/{mid}
- GET
- 입력 : mid
- 내용 : 망고 검사 정보
- 출력 : 망고 검사 정보

+ /api/disease/lists/delete/{mid}
- DELETE
- 입력 : pathVariable mid(망고 객체 id)
- 내용 : 해당 mid를 가진 망고 삭제
- 출력 : "successful delete mango" 문자열

+ /api/farmingInfo/{fid}
- GET
- 입력: fid
- 내용 : 망고 팁 내용
- 출력 : 망고 팁

