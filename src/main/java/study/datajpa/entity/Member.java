package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;

    // entity 는 기본생성자 반드시 있어야 한다.
    protected Member(){

    }
    public Member(String username){
        this.username=username;
    }

    public void changeUsername(String username){
        this.username = username;
    }
}
