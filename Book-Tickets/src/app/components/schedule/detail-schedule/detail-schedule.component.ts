import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BusStation } from '@model/bus-station';
import { BusSchedule } from 'src/app/model/bus-router';
import { BusRouterServiceService } from 'src/app/services/router/bus-router-service.service';
import { BusStationServiceService } from 'src/app/services/station/bus-station-service.service';

@Component({
  selector: 'app-detail-schedule',
  templateUrl: './detail-schedule.component.html',
  styleUrls: ['./detail-schedule.component.css']
})
export class DetailScheduleComponent implements OnInit {
  date:any;

  routeId!:Int16Array;
  route!:BusSchedule;

  busStationInfor:BusStation = {} as BusStation;

  times:any[] = [];


  constructor(private activeRoute:ActivatedRoute
    , private busRouterService: BusRouterServiceService
    , private busStationService: BusStationServiceService
    , private router: Router
    ) { }

  ngOnInit(): void {
    this.routeId = this.activeRoute.snapshot.params['id'];
    this.getRouteDetail();
    this.setDate();
    this.getTime(this.routeId);
    this.getBusStationById(this.route.ben_xe_di_id);
  }

  setDate(){
    let now = new Date();
    return this.date = now.getFullYear()+ '-' + ('0' + (now.getMonth() + 1)).slice(-2)  + '-' + ('0' + now.getDate()).slice(-2);
  }

  getRouteDetail(){
    this.route = JSON.parse(sessionStorage.getItem('routeDetail')!);
    console.log(this.route);
  }

  getBusStationById(busStationsId: Int16Array){
    this.busStationService.getBusStationDetailById(busStationsId).subscribe(
      data => {
        console.log("data");
        console.log(data);
        this.busStationInfor = data.data;
      }
    )
  }

  getTime(routerId:any){
    var dd = new Date();
    var value = ('0' + dd.getDate()).slice(-2)+'/'+('0' + (dd.getMonth() + 1)).slice(-2) +"/"+dd.getFullYear();

    this.busRouterService.getRunTime(routerId,value).subscribe(data=>{
      this.times = data.data;
      console.log(data);
    });
  }

  onBookTicket(){
    sessionStorage.setItem("date", JSON.stringify(this.date));
    this.router.navigate(["/select-route",this.routeId]);
  }

}
