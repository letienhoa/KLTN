import { CustomerInfor } from './../../../model/customer';
import { District, City } from '@model/city';
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormControl,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { CityServiceService } from 'src/app/services/city/city-service.service';
import { CustomerServiceService } from 'src/app/services/customer/customer-service.service';

@Component({
  selector: 'app-infor-customer',
  templateUrl: './infor-customer.component.html',
  styleUrls: ['./infor-customer.component.css'],
})
export class InforCustomerComponent implements OnInit {
  logInSuccess: any;
  oneWay = true;

  isSubmit = false;
  isAcceptPolicy = false;

  customerForm!: FormGroup;

  cities: City[] = [];
  districts: District[] = [];

  customerInfor: CustomerInfor = {} as CustomerInfor;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private toastr: ToastrService,
    private cityService: CityServiceService,
    private customerService: CustomerServiceService
  ) {}

  ngOnInit(): void {
    this.customerForm = this.onCreateForm(this.customerForm);
    this.onGetCities();
    this.load();
  }

  load(){
    this.logInSuccess = JSON.parse(localStorage.getItem("LogInSuccess")!);
    if(this.customerInfor.thanhPho==undefined){
      this.customerInfor.thanhPho = '';
    }
    if(this.logInSuccess!=null){
      this.customerService.getCustomerInfor(this.logInSuccess.id).subscribe(
        data => {
          this.customerInfor = data.data;
          this.onSetValueForm(this.customerInfor);
        },error => {
          console.log(error);
        }
      )
    }
  }

  onSetValueForm(data: any){
    this.customerForm.controls.customerName.setValue(data.tenKh);
    this.customerForm.controls.phone.setValue(data.sdt);
    this.customerForm.controls.email.setValue(data.email);
    this.customerForm.controls.city.setValue(data.thanhPho);
    this.customerForm.controls.district.setValue(data.quanHuyen);
    
  }

  onContinue() {
    this.isSubmit = true;
    if (this.customerForm.invalid) {
      return;
    }
    if (!this.isAcceptPolicy) {
      this.toastr.warning(
        'Xin hãy chấp nhận điều khoản của chúng tôi.'
      );
      return;
    }

    let customerInfor = {
      name: this.customerForm.controls.customerName.value,
      phone: this.customerForm.controls.phone.value,
      email: this.customerForm.controls.email.value,
      city: this.customerInfor.thanhPho,
      district: this.customerForm.controls.district.value,
    };

    console.log(customerInfor); 
    sessionStorage.setItem("step3",JSON.stringify(customerInfor));
    this.router.navigate(["/confirm-infor",customerInfor.email])
  }

  onGetCities() {
    this.cityService.getAllCity().subscribe(
      (data) => {
        this.cities = data;
      },
      (error) => {
        console.log(error);
        this.cities = [];
      }
    );
  }

  onCreateForm(fb: FormGroup) {
    fb = this.fb.group({
      customerName: new FormControl(null, Validators.required),
      phone: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      city: new FormControl(null, Validators.required),
      district: new FormControl(null, Validators.required),
    });
    return fb;
  }

  onCheck() {
    this.isAcceptPolicy = !this.isAcceptPolicy;
  }

  onSelectCity(city: any) {
    this.customerInfor.thanhPho = this.cities.find(c=>c.id==city.value)?.name!;
    this.cityService.getAllDistrict(city.value).subscribe(
      (data) => {
        this.districts = data;
        console.log(data);
        this.customerForm.controls.district.setValue(this.districts[0].name);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
