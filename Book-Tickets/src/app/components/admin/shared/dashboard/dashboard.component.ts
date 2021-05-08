
import { Component, OnInit } from '@angular/core';

declare var $:any

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  isShowComponent = 0;

  constructor() { }

  ngOnInit(): void {
    this.load();
  }

  load(){
    this.onCheckClickMenu(0);
    this.onSelectComponent(0);
  }

  onShowAdmin(){
    $("#infor-user").toggle('show');
  }

  onCheckClickMenu(index:any){
    var listItem = document.getElementsByClassName("list-items");
 
    var listLi = listItem[0].getElementsByClassName("item-admin");
  
    for(let i = 0; i< 8; i++){
      if(i!=index)
        listLi[i].classList.add('no-pick');
      else{
        listLi[i].classList.add('pick');
        listLi[i].classList.remove('no-pick');
      }
    }

    this.onSelectComponent(index);
  }

  onSelectComponent(index:any){
    this.isShowComponent = index;
  }

}
