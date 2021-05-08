import { error } from '@angular/compiler/src/util';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Customer, LogIn } from 'src/app/model/customer';
import { CustomerServiceService } from 'src/app/services/customer/customer-service.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Output() signUp = new EventEmitter<Boolean>(false);
  @Output() logInSuccess = new EventEmitter<Object>();

  formLogIn!: FormGroup;

  customerInfor!: Customer;

  constructor(private fb: FormBuilder, 
    private customerService: CustomerServiceService,
    private toastr: ToastrService) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm(){
   this.formLogIn = this.fb.group({
     userName:[null,[Validators.required]],
     passWord:[null,[Validators.required]]
   })
  }

  
  onLogIn(){
    var logInInfor : LogIn = {
      tai_khoan : this.formLogIn.get('userName')?.value,
      mat_khau : this.formLogIn.get('passWord')?.value
    }

    this.customerService.postLogIn(logInInfor).subscribe(
      data => {
        let logInStatus = {
          status: true,
          role: [],
          tenKh:"",
          gmail:""
        }
        this.customerInfor = data;
        if(this.customerInfor.Roles==null){
          logInStatus = {
            status:false,
            role: [],
            tenKh:"",
            gmail:""
          }
          this.logInSuccess.emit(logInStatus);
          this.toastr.warning('Sai tài khoản mật khẩu!');

        }
        else {
          logInStatus = {
            status:true,
            role: this.customerInfor.Roles,
            tenKh: this.customerInfor.ten_khach_hang,
            gmail: this.customerInfor.email
          }
          this.logInSuccess.emit(logInStatus);
          localStorage.setItem("LogInSuccess", JSON.stringify(this.customerInfor))
          console.log("Token after login");
          console.log(this.customerInfor);
        }
      }
    ) 
  }

  onLogInWithGmail(){
    
  }

  onSignUp(){
   this.signUp.emit(true)
  }

}
