# theMovieDB

## 개요
- 엔티티 설계
- 영화 상세 조회 API 개발
- GET /v1/movies/{movieId}
- 트렌드 및 인기에 대한 기준을 컨텐츠 상세 조회 수로 설정
- 유형별 인기 영화 목록 API (What's Popular List) -> GET /v1/movies/playType
- 유형별 최신 예고편 목록 API -> GET /v1/movies/trailer
- Free To Watch 영화 목록 API -> GET /v1/movies/mediaType
- 기간별 트렌딩 조회 API 
- GET /v1/movies/trends


## 작업 사항
- MovieArtist : 영화와 출연진 간의 ManyToMany 연관관계를 위한 브릿지 테이블 설계
- Keyword : 키워드와 영화 간의 ManyToOne 연관관계
- Genre : 장르 처리를 위한 엔티티 설계 (장르의 경우 검색필터에 사용되기 때문에 비정규화가 아닌 테이블로 분리)
- MovieAttachment : 최신 예고편 조회를 위한 첨부파일 안에 영상, 포스터, 배경 분리하는 상태값인 type 컬럼 추가
- Artist: 감독과 출연진 모두 insert 될 테이블 설계 role 로 감독 혹은 배우 분리

---

- 기간별 트렌드 컨텐츠 조회를 위해 해당 API 가 호출될 때마다 Trend 테이블에 1row씩호출 당시 시간이 함께 insert 된다.
- 인기 영화 조회를 위해 Movie 테이블에 hitCount(조회 수) 컬럼을 사용한다.
- -> 인기 영화 조회 마다 각 컨텐츠에 대한 조회수 합계를 구하게 된다면 상당한 부하가 우려되어 컬럼으로 따로 설정한다.

- Trend 테이블에 insert 발생 시 domainEvent를 발생시켜 해당 Movie의 조회수를 업데이트 해준다.
- ex) Trend 테이블의 Movie A 에 대한 row 수 == Movie A 의 hitCount

---

## API 명세
- 모든 리스트는 캐러셀 형식으로 출력되기 때문에 페이징처리는 제외했다.
- GET /v1/movies/{movieId}
-> 컨텐츠 상세 조회
- GET /v1/movies/playType 
-> 유형별 리스트 조회
-> STREAMING, TV, RENTAL, THEATER 해당 내용을 RequestParam으로 선택하여 request 
- GET /v1/movies/mediaType
-> main 화면의 free to watch
-> MOVIE, TV 해당 내용을 RequestParam으로 선택하여 request 
- GET /v1/movies/trailer
-> 최신 예고편 리스트 조회
-> STREAMING, TV, RENTAL, THEATER 해당 내용을 RequestParam으로 선택하여 request 
- GET /v1/movies/trends
-> 기간별 트렌드 조회
-> 오늘 일때 : ?term=TODAY
-> 이번주 일 때 : ?term=WEEK&startedAt=2022-10-10&endedAt=2022-10-17
