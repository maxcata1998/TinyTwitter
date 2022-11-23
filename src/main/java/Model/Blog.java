package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blog")
public class Blog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(nullable = false, name = "title")
  private String title;

  @Column(nullable = false, name = "author")
  private int author;


  public Blog(int id, String title, int author){
    this.id = id;
    this.title =title;
    this.author = author;
  }

  public Blog(){}

  public void setId(int id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getTitle() {
    return this.title;
  }

  public int getId() { return this.id; }

  public int getAuthor() { return this.author; }
}
