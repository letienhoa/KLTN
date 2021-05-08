export interface LogIn{
    tai_khoan:string;
    mat_khau: string;    
}

export interface Customer{
    id:Int16Array;
    ten_khach_hang: string;
    email: string;
    discount: Int16Array;
    Token: string;
    Roles: [];
}

export interface CustomerInfor{
    id:Int16Array;
    taiKhoan:string;
    tenKh:string;
    email:string;
    sdt:string;
    cmnd:string;
    diaChi:string;
    thanhPho:string;
    quanHuyen:string;
    status:Int16Array;
    diemTichLuy:Int16Array;
    discount:Int16Array;
    tongDiem:Int16Array;
    updatedAtFormatVN: Date;
}

export interface UpdateCustomer{
    id:Int16Array;
    email:string;
    ten:string;
    sdt:string;
    cmnd:string;
    dia_chi:string;
    thanh_pho:string;
    quan_huyen:string;
}
