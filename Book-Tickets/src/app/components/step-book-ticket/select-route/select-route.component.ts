import { HostListener } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BusRouterInfor, BusSchedule } from '@model/bus-router';
import { BusStation } from '@model/bus-station';


import { ToastrService } from 'ngx-toastr';
import { PaypalServiceService } from 'src/app/services/paypal/paypal-service.service';
import { BusRouterServiceService } from 'src/app/services/router/bus-router-service.service';
import { BusStationServiceService } from 'src/app/services/station/bus-station-service.service';

declare var $:any

@Component({
  selector: 'app-select-route',
  templateUrl: './select-route.component.html',
  styleUrls: ['./select-route.component.css']
})
export class SelectRouteComponent implements OnInit {

  routeId: any;
  today:any;
  returnDay:any

  placeGo: string = "";
  placeDes: string = "";

  routerInfor:BusRouterInfor = {} as BusRouterInfor;

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

  listSearchToGo:BusStation[] = [] as BusStation[];
  listSearchToDestination:BusStation[] = [] as BusStation[];

  busStations: BusStation[]  = [] as BusStation[];
  busStationsByID: BusStation[]  = [] as BusStation[];

  bustationById: BusSchedule = {} as BusSchedule;

  constructor( private router: Router
    ,private busStationService: BusStationServiceService
    ,private busRouterService: BusRouterServiceService
    ,private toastr: ToastrService
    ,private activateRoute: ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.routeId = this.activateRoute.snapshot.params['id'];
  
    this.getDate(JSON.parse(sessionStorage.getItem("date")!));
    this.getBusRouterInfor(this.routeId);
    this.getAllBusStation();
  }

  getBusRouterInfor(busRouteId: Int16Array){
    this.busRouterService.getRouterInforById(busRouteId).subscribe(
      data => {
        this.routerInfor = data.data;
        this.placeGo = this.routerInfor.diem_di;
        this.placeDes = this.routerInfor.diem_toi;

        this.bookTicketStep1.destination.ben_toi = this.placeDes;
        this.bookTicketStep1.departure.ben_toi = this.placeGo;

        this.bookTicketStep1.departure.id =  this.routerInfor.diem_di_id;
        this.bookTicketStep1.destination.id =  this.routerInfor.diem_toi_id;

        console.log(this.routerInfor);
      }
    )
  }

  getDate(time:string){
    let day:any;
    let tomorrow:any;
    if(time==null){
      day = new Date();
      tomorrow = new Date(new Date().getTime()+(24*60*60*1000));
    }
    else{
      day = new Date(time);
      tomorrow = new Date(new Date(time).getTime()+(24*60*60*1000));
    }
 
    let dayInput = (<HTMLInputElement>document.getElementById("go-date"));
    dayInput.value = day.getFullYear()+ '-' + ('0' + (day.getMonth() + 1)).slice(-2)  + '-' + ('0' + day.getDate()).slice(-2);
    this.today = dayInput.value;
    this.returnDay = tomorrow.getFullYear()+ '-' + ('0' + (tomorrow.getMonth() + 1)).slice(-2)  + '-' + ('0' + tomorrow.getDate()).slice(-2);
    this.bookTicketStep1.daygo = ('0' + day.getDate()).slice(-2)+"/"+('0' + (day.getMonth() + 1)).slice(-2)+"/"+day.getFullYear();
    this.bookTicketStep1.returnday = ('0' + tomorrow.getDate()).slice(-2)+"/"+('0' + (tomorrow.getMonth() + 1)).slice(-2)+"/"+tomorrow.getFullYear();
  }



  getAllBusStation(){
    this.busStationService.getAllBusStation().subscribe(
      data => {
        this.busStations = data.data;
        console.log(this.busStations);
      }
    )
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
          if(this.listSearchToDestination[0]!=undefined)
            this.placeDes = this.listSearchToDestination[0].ben_toi;
         
          this.listSearchToGo = [];
        }
    }
    else{
        dataList[1].classList.add("show");  
        dataList[0].classList.remove("show");

      if(!this.onCheckValue(this.listSearchToGo, this.placeGo)) {
        if(this.listSearchToGo[0]!=undefined)
          this.placeGo = this.listSearchToGo[0].ben_toi;
        this.listSearchToDestination = [];
      }
        
    }
  }

  onCheckValue(listData:any, keyWork:any){
    for(let i of listData){
      if(i.name==keyWork)
        return true;
    }
   return false;
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

  getAllBusStationsById(id:Int16Array){
    this.busStationService.getDestinationStationsById(id).subscribe(
      data => {
        this.busStationsByID = data.data;
        console.log(this.busStationsByID);
        this.onGetRouteById(this.busStationsByID)
      }
    );
  }

  onGetRouteById(data:any[]){
    this.bustationById = data.find(x=>x.id==this.routeId);
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

  onContinue(){
    this.onSearch();
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
