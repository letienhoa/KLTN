import { EventEmitter } from '@angular/core';
import { Component, OnInit, Input, Output } from '@angular/core';
import { Seat } from 'src/app/model/seats';


@Component({
  selector: 'app-type-car',
  templateUrl: './type-car.component.html',
  styleUrls: ['./type-car.component.css']
})

export class TypeCarComponent implements OnInit {
    @Input() listSeat!:Seat;
    @Output() toggledChange: EventEmitter<Seat> = new EventEmitter<Seat>();
    
    constructor() { 
    }
  
    ngOnInit(): void {
        this.listSeat = {} as Seat;
        this.load();
    }
  
    onChangeTime(){
      this.toggledChange.emit(this.listSeat);
    }
  
    load(){
      /* this.listSeatsRow = this.setFloors(this.floors,this.ListSeat);
      if(this.listSeatsRow.length == 2){
          this.listSeatsRow2 = this.listSeatsRow.pop();
          this.listSeatsRow1 = this.listSeatsRow.pop();
      }
      else{
          this.listSeatsRow1 = this.listSeatsRow;
      } */
    }
  
    setFloors(listSeats:Seat){
      /* var numberSeats = listSeats.length;
      var listSeat:any[] = [];
  
      if(floors == 1){
          var floors1= document.getElementsByClassName('floor-1');
          floors1[0].classList.add('left');
  
          if(numberSeats<=16){
              var row = (numberSeats - numberSeats%3)/3;
              if(row>1) row+=1;
              var seats:any = {seat:[0,0,0,0,listSeats.splice(0,1)]};
              listSeat.push(seats);
  
              for(let i = 0;i<row;i++){
                  seats = {
                  seat:[listSeats.splice(0,1),0,listSeats.splice(0,1),0,listSeats.splice(0,1)]
                  }
                  listSeat.push(seats)
              }
              return listSeat;
          }
          if(numberSeats>16&&numberSeats<=26){
              var row = (numberSeats -numberSeats%4)/4;
              if(row>1) row+=1;
              var seats:any = {seat:[0,0,0,0,listSeats.splice(0,1)]};
              listSeat.push(seats);
  
              for(let i = 0;i<row;i++){
                  seats = {
                  seat:[listSeats.splice(0,1),listSeats.splice(0,1),0,listSeats.splice(0,1),listSeats.splice(0,1)]
                  }
                  listSeat.push(seats)
              }
              
              return listSeat;
          }
      }
      else{
          var listSeatsFloors1:any[] = [];
          var listSeatsFloors2:any[] = [];
  
          var listSeatsTemp = [...listSeats];
  
          if(numberSeats/2<=12){
              var row = numberSeats/4;
              
              for(let i = 0;i<row;i++){
                  var seats:any = {seat:[0,listSeats.splice(0,1),0,listSeats.splice(0,1),0]};
                  listSeatsFloors1.push(seats);
  
                  var seats:any = {seat:[0,listSeatsTemp.splice(row*2,1),0,listSeatsTemp.splice(row*2,1),0]};
                  listSeatsFloors2.push(seats);
              }
  
              listSeat.push(listSeatsFloors1);
              listSeat.push(listSeatsFloors2);
  
              return listSeat;
          }
          if(numberSeats/2>12){
              var row = (numberSeats/2-7)/3;
              var listSeatsTemp = [...listSeats];
  
              for(let i = 0;i<row;i++){
                  var seats:any = {seat:[listSeats.splice(0,1),0,listSeats.splice(0,1),0,listSeats.splice(0,1)]};
                  listSeatsFloors1.push(seats);
  
                  var seats:any = {seat:[listSeatsTemp.splice(numberSeats/2,1),0,listSeatsTemp.splice(numberSeats/2,1),0,listSeatsTemp.splice(numberSeats/2,1)]};
                  listSeatsFloors2.push(seats);
              }
  
                  listSeatsFloors1.push({seat:[listSeats.splice(0,1),0,0,0,listSeats.splice(0,1)]});
                  listSeatsFloors1.push({seat:[listSeats.splice(0,1),listSeats.splice(0,1),listSeats.splice(0,1),listSeats.splice(0,1),listSeats.splice(0,1)]});
  
                  listSeatsFloors2.push({seat:[listSeatsTemp.splice(numberSeats/2,1),0,0,0,listSeatsTemp.splice(numberSeats/2,1)]});
                  listSeatsFloors2.push({seat:[listSeatsTemp.splice(numberSeats/2,1),listSeatsTemp.splice(numberSeats/2,1),listSeatsTemp.splice(numberSeats/2,1),listSeatsTemp.splice(numberSeats/2,1),listSeatsTemp.splice(numberSeats/2,1)]});
  
              listSeat.push(listSeatsFloors1);
              listSeat.push(listSeatsFloors2);
  
              return listSeat;
          }
      }
      */
      return [];
    }

    setSeatsFloor(listSeats:Seat){
        let listSeat = [];
        if(listSeats.list_giuong.length==16){
            var seatsOfRow = {
                seat:[0,0,0,0,listSeats.list_giuong.shift()]
            }
            listSeat.push(seatsOfRow);
        }
    }
  
    onBook(value:any){
     
    /*   var td = document.getElementById('seat');
      var dis = document.getElementsByClassName('disable');
      var tdS = document.getElementsByClassName('seat');
  
      var length = this.listSeatsChoose.length;
      var index = this.oneWay == false?tdS.length/2+value.stt:value.stt;
   
      if(dis[index-1]==null){ 
          if(tdS[index-1].classList.toggle('select')==true){
              if(length+1==6){
                  tdS[index-1].classList.toggle('select');
                  return alert("Chi duoc chon 5 ve");
              }
              this.listSeatsChoose.push(value);
              this.totalPrice += this.price;
          }
          else {
              for(let i = 0; i < length; i++){
                  if(this.listSeatsChoose[i].stt === index){
                      this.listSeatsChoose.splice(i,1);
                      this.totalPrice -= this.price;
                  }
              }
          } 
      } */
    }

    setFloors1(listSeats:Seat){
        
    }
  
}

