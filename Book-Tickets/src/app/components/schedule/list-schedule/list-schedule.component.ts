import { element } from 'protractor';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { BusSchedule } from 'src/app/model/bus-router';
import { BusRouterServiceService } from 'src/app/services/router/bus-router-service.service';
import { fromEvent } from 'rxjs';
import {
  debounceTime,
  map,
  distinctUntilChanged
} from "rxjs/operators";
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-schedule',
  templateUrl: './list-schedule.component.html',
  styleUrls: ['./list-schedule.component.css']
})
export class ListScheduleComponent implements OnInit {

  @ViewChild('placeGo', {static:true})
  placeGoSearch!:ElementRef;

  @ViewChild('placeDes', {static:true})
  placeDesSearch!:ElementRef;

  busRoutes:BusSchedule[] = [];
  busRoutesSearch:BusSchedule[] = [];

  isLoad = true;

  constructor(private busRouteService:BusRouterServiceService, private route: Router) { }

  ngOnInit(): void {
    this.getAllRoute();
    this.onDebounceSearch(this.placeGoSearch,0);
    this.onDebounceSearch(this.placeDesSearch,1);
  }

  getAllRoute(){
    this.isLoad = true;
    this.busRouteService.getAllRoute().subscribe(
      data => {
        this.busRoutes = data.data;
        this.busRoutesSearch = this.busRoutes;
        this.isLoad = false;
      }
    )
  }

  onSearchRoute(value:string, type: number){
    let listRoute: BusSchedule[] = this.busRoutes;
    if(value==""){
      this.busRoutes = this.busRoutesSearch;
      return;
    }

    if(type==0){
      listRoute = listRoute.filter(r => r.ben_xe_di.toLocaleLowerCase().includes(value.toLocaleLowerCase()));
    }
    else{
      listRoute = listRoute.filter(r => r.ben_xe_toi.toLocaleLowerCase().includes(value.toLocaleLowerCase()));
    }
    this.busRoutes = [...listRoute];
  }

  onDetail(busRoute:BusSchedule){
    sessionStorage.setItem('routeDetail',JSON.stringify(busRoute))
    this.route.navigate(['/schedule/detail',busRoute.id]);
  }

  onDebounceSearch(element:ElementRef, type:number){
    fromEvent(element.nativeElement,"keyup").pipe(
      map((event:any)=>{
        return event.target.value;
      })
      ,debounceTime(100)
      ,distinctUntilChanged()
    ).subscribe((text:string)=>{
      this.onSearchRoute(text,type);
    })
  }

  onBookTicket(busRoute:BusSchedule){
    this.route.navigate(['/select-route',busRoute.id]);
  }

}
