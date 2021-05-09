import { Component, OnInit } from '@angular/core';
import { TicketServiceService } from 'src/app/services/ticket/ticket-service.service';
import { ToastrService } from 'ngx-toastr';
import { PaypalServiceService } from 'src/app/services/paypal/paypal-service.service';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css'],
})
export class InvoiceComponent implements OnInit {
  
  isShow = false;
  ticket!: any;
  ticketCode = "";

  constructor(
    private ticketService: TicketServiceService,
    private toastr: ToastrService,
    private payPalSerice: PaypalServiceService
  ) {}

  ngOnInit(): void {
  }

  /* ⇒ */

  onSearch() {
    this.ticketService.getTicketByCode(this.ticketCode).subscribe(
      (data) => {
         
        this.ticket = data.data;
        
        this.isShow = true;
        this.ticket.tuyen_xe = this.ticket.tuyen_xe.replace('->',"⇒");
        this.ticket.slots = this.setSeatName(this.ticket.slots);
        console.log(this.ticket);
      },
      (error) => {
        console.log(error);
        this.isShow = false;
        this.toastr.warning('Xin hãy nhập lại mã vé', 'Mã vé sai');
      }
    );
  }

  setSeatName(seat:any){
    let seats = [];
    for(let i of seat){
      if(i<10){
        seats.push("A0"+i);
      }
      else if(i>=10&&i<=22){
        seats.push("A"+i);
      }
      else if(i-22<10&&i-22>0){
        seats.push("B0"+(i-22));
      }
      else{
        seats.push("B"+(i-22));
      }
    }
    return seats;
  }

  onBack() {
    this.isShow = false;
  }

  onCancel(billId: Int16Array) {
    this.payPalSerice.getCancelRoute(billId).subscribe(
      data => {
        console.log(data);
        this.toastr.success("Hủy vé thành công");
      },error=>{
        console.log(error);
        this.toastr.warning("Hủy vé thất bại");
      }
    )
  }
}
