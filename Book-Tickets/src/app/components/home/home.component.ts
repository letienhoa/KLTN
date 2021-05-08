import { element } from 'protractor';
import { ElementRef, ViewChild } from '@angular/core';
import { HostListener } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {BrowserModule} from '@angular/platform-browser';
import { Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { BusStationServiceService } from 'src/app/services/station/bus-station-service.service';
import {BusStation} from '../../model/bus-station';
import { BusRouterServiceService } from 'src/app/services/router/bus-router-service.service';
import { BusRouter, BusRouterPopular } from 'src/app/model/bus-router';
import { ToastrService } from 'ngx-toastr';

declare var $:any

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  
})
export class HomeComponent implements OnInit {

  notifycation = false;

  today:any;
  returnDay:any

  listSearchToGo:BusStation[] = [];
  listSearchToDestination:BusStation[] = [];

  placeGo: string = "";
  placeDes: string = "";
  
  busStations: BusStation[]  = [];
  busStationsByID: BusStation[]  = [];

  busRoutesPopular: BusRouterPopular[] = [];

  bookTicketStep1 = {
    departure: {
      id:'',
      ben_toi:''
    },
    destination: {
    id:'',
    ben_toi:''
    },
    daygo:"",
    returnday:"",
    isOneWay:true,
  } 

  thisElementClicked: boolean = false;

  constructor( private router: Router
    ,private busStationService: BusStationServiceService
    ,private busRouterService: BusRouterServiceService
    ,private toastr: ToastrService) { }

  ngOnInit(): void {
    this.load();
  }

  load(){
    this.showSlide();
    this.getDate();
    this.getAllBusStation();
    this.getRoutesPopular();
  }

  showSlide(){
    $('.multiple-items').slick({
      infinites:true,
      slidesToShow:5,
      slidesToScroll:1,
      arrows:false,
      dots:false,
      autoplay: true,
      autoplaySpeed: 2000,
      centerMode:true,
      centerPadding:'0',
      pauseOnHover: false,
      responsive: [
        {
          breakpoint: 1100,
          settings: {
            arrows: false,
            centerMode: true,
            centerPadding: '40px',
            slidesToShow: 4
          }
        },
        {
          breakpoint: 900,
          settings: {
            arrows: false,
            centerMode: true,
            centerPadding: '40px',
            slidesToShow: 2
          }
        },
        {
          breakpoint: 700,
          settings: {
            arrows: false,
            centerMode: true,
            centerPadding: '40px',
            slidesToShow: 1
          }
        }
      ]
    });
  }

  getDate(){
    var day = new Date();
    var tomorrow = new Date(new Date().getTime()+(24*60*60*1000));
    var dayInput = (<HTMLInputElement>document.getElementById("go-date"));
    dayInput.value = day.getFullYear()+ '-' + ('0' + (day.getMonth() + 1)).slice(-2)  + '-' + ('0' + day.getDate()).slice(-2);
    this.today = dayInput.value;
    this.returnDay = tomorrow.getFullYear()+ '-' + ('0' + (tomorrow.getMonth() + 1)).slice(-2)  + '-' + ('0' + tomorrow.getDate()).slice(-2);
    this.bookTicketStep1.daygo = ('0' + day.getDate()).slice(-2)+"/"+('0' + (day.getMonth() + 1)).slice(-2)+"/"+day.getFullYear();
    this.bookTicketStep1.returnday = ('0' + tomorrow.getDate()).slice(-2)+"/"+('0' + (tomorrow.getMonth() + 1)).slice(-2)+"/"+tomorrow.getFullYear();
  }

  dateChange(event:any, type:any){
    var today = new Date(event.value);
    var tomorrow = new Date( new Date(event.value).getTime() + (24 * 60 * 60 * 1000));
    if(type==0){
      var value = ('0' + today.getDate()).slice(-2)+'/'+('0' + (today.getMonth() + 1)).slice(-2) +"/"+today.getFullYear();
      this.bookTicketStep1.daygo = value;
      this.returnDay = tomorrow.getFullYear()+ '-' + ('0' + (tomorrow.getMonth() + 1)).slice(-2)  + '-' + ('0' + tomorrow.getDate()).slice(-2);  
      this.bookTicketStep1.returnday = ('0' + tomorrow.getDate()).slice(-2)+'/'+('0' + (tomorrow.getMonth() + 1)).slice(-2) +"/"+tomorrow.getFullYear();
      
      if(this.bookTicketStep1.isOneWay == false){
        var returnDayShow = <HTMLInputElement>document.getElementById("return-date");
        returnDayShow.value = this.returnDay;
      }
    }
    else{
      this.bookTicketStep1.returnday = ('0' + today.getDate()).slice(-2)+'/'+('0' + (today.getMonth() + 1)).slice(-2) +"/"+today.getFullYear();
    }
  }

  onCheckTyeCheck(type:any){
    var returnDayShow = <HTMLInputElement>document.getElementById("return-date");
    if(type==0){
      returnDayShow.disabled = true;
      returnDayShow.value = "";
      this.bookTicketStep1.isOneWay = true;
      this.bookTicketStep1.returnday = "";
    }else{
      var dayShow = <HTMLInputElement>document.getElementById("go-date");
      returnDayShow.disabled = false;
      var tomorrow = new Date( new Date(dayShow.value).getTime() + (24 * 60 * 60 * 1000));
      returnDayShow.value = tomorrow.getFullYear()+ '-' + ('0' + (tomorrow.getMonth() + 1)).slice(-2)  + '-' + ('0' + tomorrow.getDate()).slice(-2);  
      this.bookTicketStep1.returnday = ('0' + tomorrow.getDate()).slice(-2)+'/'+('0' + (tomorrow.getMonth() + 1)).slice(-2) +"/"+tomorrow.getFullYear();
      this.bookTicketStep1.isOneWay = false;
    }
  }

  setSelectionRange(type:any){
    
    var dataList = document.getElementsByClassName("data-list-container");
    if(type==0){
        dataList[0].classList.add("show");   
        dataList[1].classList.remove("show");

        if(!this.onCheckValue(this.listSearchToDestination, this.placeDes)) {
          this.placeDes = this.listSearchToDestination[0].ben_toi;
          this.listSearchToGo = [];
        }
    }
    else{
        dataList[1].classList.add("show");  
        dataList[0].classList.remove("show");

      if(!this.onCheckValue(this.listSearchToGo, this.placeGo)) {
        this.placeGo = this.listSearchToGo[0].ben_toi;
        this.listSearchToDestination = [];
      }
        
    }
  }

  onSelectDateOrigin(event:any, type:any){
  
    var list:BusStation[];

    if(type == 0){
      list = this.busStations;
      this.placeGo = event.value;
      
      this.listSearchToGo = [];

      list.filter((element)=>{
        if(element.ben_toi.toString().toLowerCase().includes(event.value.toLowerCase()))   
          this.listSearchToGo.push(element);
      });
    }
    else{
      list = this.busStationsByID;
      this.placeDes = event.value;

      this.listSearchToDestination = [];

      list.filter((element)=>{
        if(element.ben_toi.toString().toLowerCase().includes(event.value.toLowerCase()))   
          this.listSearchToDestination.push(element);
      });
    }
  }

  onSearch(){
    console.log(this.bookTicketStep1);
    if(this.bookTicketStep1.destination.ben_toi==""){
      this.toastr.warning('Chọn nơi đi và nơi đến !', 'Xin hãy');
      return
    }

    sessionStorage.setItem("step1",JSON.stringify(this.bookTicketStep1));

    if(!this.bookTicketStep1.isOneWay) 
      return this.router.navigate(['/select-seats-two-way']);
    return this.router.navigate(['/select-seats']);
  }

  onSelect(object:any, type:any){
    if(type==0){
      this.placeGo = object.ben_toi;
      this.getAllBusStationsById(object.id);
      this.bookTicketStep1.departure.id = object.id;
      this.bookTicketStep1.departure.ben_toi = object.ben_toi;
      this.placeDes = '';
    }
    else{
      this.placeDes = object.ben_toi;  
      this.bookTicketStep1.destination.id = object.id;
      this.bookTicketStep1.destination.ben_toi = object.ben_toi;
      this.listSearchToDestination = [];
    }
     
  }

  onCheckValue(listData:any, keyWork:any){
    for(let i of listData){
      if(i.name==keyWork)
        return true;
    }
   return false;
  }

  getAllBusStation(){
    this.busStationService.getAllBusStation().subscribe(
      data => {
        this.busStations = data.data;
        console.log(this.busStations);
      
      }
    )
  }

  getAllBusStationsById(id:Int16Array){
    this.busStationService.getStationsById(id).subscribe(
      data => {
        this.busStationsByID = data.data;
        console.log(this.busStationsByID);
      }
    );
  }

  getRoutesPopular(){
    this.busRouterService.getRouterPopular().subscribe(
      data => {
        this.busRoutesPopular = data.data;
        console.log(this.busRoutesPopular);
      }
    )
  }

  @HostListener('document:click', ['$event'])
  clickDown(e:any){
    var container = $(".select-place");
    console.log(container.is(e.target));
    console.log(container.has(e.target).length);
    console.log(e)

    if (!container.is(e.target)) 
    {
      var dataList = document.getElementsByClassName("data-list-container");
      var length = dataList.length;
      setTimeout(function(){       
        for(let i=0;i<length;i++){
          dataList[i].classList.remove('show');
        }; 
      }, 300);

      if(!this.onCheckValue(this.listSearchToGo, this.placeGo) && this.listSearchToGo.length>0) {
        this.placeGo = this.listSearchToGo[0].ben_toi;
        this.listSearchToGo = [];
      }
      
      if(!this.onCheckValue(this.listSearchToDestination, this.placeDes) && this.listSearchToDestination.length>0) {
        this.placeDes = this.listSearchToDestination[0].ben_toi;
        this.listSearchToDestination = [];
      }
    }
  }

}
