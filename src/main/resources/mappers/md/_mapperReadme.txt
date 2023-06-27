------------------------
mybatis SQL
------------------------
SQL 예약어 : 소문자
테이블, 컬럼 : 대문자
MapperName : ERD-SUB Name

        select * 
          from SUSRMA
         where COMPKEY=#{compkey} 
           and USERACT=#{useract}