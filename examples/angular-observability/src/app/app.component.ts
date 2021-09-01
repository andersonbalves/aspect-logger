import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  public name = '';
  public age = '';

  constructor(private http: HttpClient) { }

  ngOnInit() {

  }

  public salvarOpentelemety() {
    let body = {
      name: this.name,
      age: this.age
    };
    return this.http.post('http://localhost:9080/user',
      body
    ).subscribe(response => response)
  }

  public salvarOpentracing() {
      let body = {
        name: this.name,
        age: this.age
      };
      return this.http.post('http://localhost:9081/user',
        body
      ).subscribe(response => response)
    }

}
