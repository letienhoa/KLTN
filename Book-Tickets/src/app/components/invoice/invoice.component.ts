import { error } from '@angular/compiler/src/util';
import { element } from 'protractor';
import { Ticket } from './../../model/ticket';
import { Component, OnInit } from '@angular/core';
import { TicketServiceService } from 'src/app/services/ticket/ticket-service.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css'],
})
export class InvoiceComponent implements OnInit {
  code = '';
  isShow = false;
  ticket!: Ticket;

  constructor(
    private ticketService: TicketServiceService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.ticket = {} as Ticket;
  }

  onSearch() {
    this.ticketService.getTicketByCode(this.code).subscribe(
      (data) => {
         if (data.data == null) {
          this.isShow = false;
          this.toastr.warning('Xin hãy nhập lại mã vé', 'Mã vé sai');
          return;
        }else{
          this.ticket = data.data;
          console.log(this.ticket);
          this.isShow = true;
        }
      },
      (error) => {
        console.log(error);
        this.isShow = false;
        this.toastr.warning('Xin hãy nhập lại mã vé', 'Mã vé sai');
      }
    );
  }

  onBack() {}

  onCancel() {}
}
