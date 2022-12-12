package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username","age"})
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "team_id")
    private Team team;
    // entity 는 기본생성자 반드시 있어야 한다. -> lombok 어노테이션으로 대체
//    protected Member(){
//
//    }
    public Member(String username){
        this.username=username;
    }

    public Member(String username, int age) {
        this.username =username;
        this.age = age;
    }
    public Member(String username, int age, Team team) {
        this.username =username;
        this.age = age;
        if(team!=null){
            changeTeam(team);
        }
    }

    public void changeUsername(String username){
        this.username = username;
    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
