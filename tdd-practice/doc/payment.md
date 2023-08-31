## TDD 대역(double) 연습 : 결제 수단 등록 기능

- 대역 : 다른 누군가에게 위임해서 대역으로 배치하여 사용..
  2가지 타입의 대역을 사용 -> Mockito, 가짜 구현
  Test의 대상 -> PayMethodRegister (대역을 사용하는 대상 : 구현 과정에서 당장 구현하지 않을 것들 ex. 외부 연결.. )
  DB연동과 관련하여서는 모의객체를 사용하면 더 힘든 상황이 벌어질 수 있어서  메모리 구현체 사용하는 것이 좀 더 편하다.

결제 수단 등록 요청
* 사용자 ID
* 카드번호
* 소유자명

결제 수단 정보 구성
* 식별자
* 사용자 Id
* 카드번호
* 카드 소유자
* 신청일

예외 상황
* 요청 정보 유효성 검사: 값 자체
* 사용자 존재 여부 확인
* 유효한 카드 번호인지 확인
* 동일카드번호로 등록했는지 확인

(번외)  
dto에서 -> entity로 변환하는 과정에서, dto 내에 바로 entity 변환을 두기보다는
그 상황에서 메서드로 정의하거나 factory Method를 따로 두는 것을 선호한다고 한다.
좋고 나쁘고의 문제는 아닌것 같고 선호의 문제로 파악하는게 좋겠다.  