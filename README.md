# AlipayApi
알리페이 api 오픈소스 분석

2017.11.03 알리페이 웹 환경 결제승인, 환불, 승인상세정보 조회 테스트

2017.10.26 refund(환불) 로직 분석 리턴 데이터 테스트

2017.10.20 결제정보 및 REQUEST,RESPONSE 데이터 확인 notify_url, return_url분석, 결제승인 로직 분석

2017.10.13 api 문서 및 다이어그램 분석, 전문 파라미터 정보 분석

테스트 결제 계정정보 테스트 계정 :
패스워드 : 
인증키 : 
게이트웨이 :

API 

1.QR코드기반결재방식
![1](https://user-images.githubusercontent.com/12209348/36959867-b09de922-2087-11e8-8d0e-41d8bb37439a.png)

2.알리페이에개인계좌연동되어있는아이디로결재
![2](https://user-images.githubusercontent.com/12209348/36959870-b27bf338-2087-11e8-89a8-698cd7c02e05.png)

3. 결재승인후해당물품에대한정보를기입하여전문을송신받아최종결재완료
![default](https://user-images.githubusercontent.com/12209348/36959872-b3b59efc-2087-11e8-8aef-254339fa4ee7.png)
