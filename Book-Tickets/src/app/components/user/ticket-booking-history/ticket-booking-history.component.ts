import { Ticket } from './../../../model/ticket';
import { Component, OnInit, ViewChild } from '@angular/core';
import { TicketServiceService } from 'src/app/services/ticket/ticket-service.service';
import { BusRouterServiceService } from 'src/app/services/router/bus-router-service.service';
import { BusSchedule } from 'src/app/model/bus-router';
import { ElementRef } from '@angular/core';
import { fromEvent } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ticket-booking-history',
  templateUrl: './ticket-booking-history.component.html',
  styleUrls: ['./ticket-booking-history.component.css']
})
export class TicketBookingHistoryComponent implements OnInit {
  @ViewChild('codeTicket', { static: true })
  codeTicket!: ElementRef;

  customerInfor:any;
  tickets!:Ticket[];
  ticketsSearch!:Ticket[];

  busRoutes!:BusSchedule[];

  constructor(
    private ticketService:TicketServiceService, 
    private busRouteService:BusRouterServiceService,
    private route: Router
    ) { }

  ngOnInit(): void {
    this.tickets = [] as Ticket[];
    this.ticketsSearch = [] as Ticket[];
    this.busRoutes = [] as BusSchedule[];

    this.customerInfor = JSON.parse(localStorage.getItem("LogInSuccess")!);
    console.log(this.customerInfor);
    this.onGetTicketHistory(this.customerInfor.id);
    this.onGetRoutes();
    this.onDebounceSearch(this.codeTicket);
  }

  onDebounceSearch(element: ElementRef){
    fromEvent(element.nativeElement,"keyup").pipe(
      map((event:any) => {
        return event.target.value;
      })
      ,debounceTime(100)
      ,distinctUntilChanged()
    ).subscribe((text:string)=>{
      this.onSearch(text);
    })
  }

  onGetTicketHistory(id:Int16Array){
    this.ticketService.getTicketHistory(id).subscribe(
      data => {
        this.tickets = data.data;
        this.ticketsSearch = this.tickets;
      }
    )
  }

  onGetRoutes(){
    this.busRouteService.getAllRoute().subscribe(
      data => {
        this.busRoutes = data.data;
        console.log(this.busRoutes);
      }
    )
  }

  onSearchByRoute(event:any){
    let ticketsTem = [];
    for(let i of this.ticketsSearch){
      if(i.id_tuyen_xe == event.value)
        ticketsTem.push(i);
    }
    this.tickets = ticketsTem;
  }

  onSearchByStatus(event:any){
    let ticketsTem = [];
    for(let i of this.ticketsSearch){
      if(i.trang_thai == event.value)
        ticketsTem.push(i);
    }
    this.tickets = ticketsTem;
  }

  onChangeDate(event:any){
    let ticketsTem = [];
    for(let i of this.tickets){
      let date = new Date(i.ngay_chay);
      let ticketDate = (date.getFullYear()+ '-' + ('0' + date.getDate()).slice(-2) +'-' + ('0' + (date.getMonth() + 1)).slice(-2));
      if(ticketDate == event.value){
        ticketsTem.push(i);
      }
    }
    this.tickets = ticketsTem;
  }

  onRefresh(){
    this.tickets = this.ticketsSearch;
  }

  onSearch(name:string){
    if(name==""){
      this.onRefresh();
      return;
    };
    let ticketsTem = [];
    for(let i of this.ticketsSearch){
      if(i.code.toLowerCase().includes(name.toLowerCase())){
        console.log(i.code);
        ticketsTem.push(i);
      }
    }
    this.tickets = ticketsTem;
  }

  onBookTiket(){
    this.route.navigate(["/"]);
  }
}
