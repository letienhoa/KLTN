import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BusSchedule } from 'src/app/model/bus-router';
import { BusRouterServiceService } from 'src/app/services/router/bus-router-service.service';

@Component({
  selector: 'app-detail-schedule',
  templateUrl: './detail-schedule.component.html',
  styleUrls: ['./detail-schedule.component.css']
})
export class DetailScheduleComponent implements OnInit {

  routeId!:Int16Array;
  route!:BusSchedule;

  listSang:any[] = [];
  listChieu:any[] = [];
  listToi:any[] = [];

  constructor(private activeRoute:ActivatedRoute, private busRouterService: BusRouterServiceService) { }

  ngOnInit(): void {
    this.routeId = this.activeRoute.snapshot.params['id'];
    this.getRouteDetail();
    this.getTime(this.routeId);
  }

  getRouteDetail(){
    this.route = JSON.parse(sessionStorage.getItem('routeDetail')!);
  }

  getTime(routerId:any){
    var dd = new Date();
    var value = ('0' + dd.getDate()).slice(-2)+'/'+('0' + (dd.getMonth() + 1)).slice(-2) +"/"+dd.getFullYear();

    this.busRouterService.getRunTime(routerId,value).subscribe(res=>{
      for(let i of res.data){
        if(i.giochay<12)
          this.listSang.push(i.giochay)
        else if(i.giochay>=12&&i.giochay<17)
          this.listChieu.push(i.giochay)
        else
          this.listToi.push(i.giochay)
      }
    });
  }

}
