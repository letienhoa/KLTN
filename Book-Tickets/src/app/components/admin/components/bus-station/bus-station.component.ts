import { tick } from '@angular/core/testing';
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormControl,
} from '@angular/forms';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { BusStation } from '@model/bus-station';
import { BusStationServiceService } from 'src/app/services/station/bus-station-service.service';
import { ToastrService } from 'ngx-toastr';
import { ImageDriveService } from 'src/app/services/image-drive/image-drive.service';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, Label } from 'ng2-charts';

type NewType = ChartOptions;

@Component({
  selector: 'app-bus-station',
  templateUrl: './bus-station.component.html',
  styleUrls: ['./bus-station.component.css'],
})
export class BusStationComponent implements OnInit {
  @ViewChild('myInput',{static: false})
  myInputVariable!:ElementRef;
  
  year!: number;

  inputSearch = '';

  srcImage: any = '';
  picture: any = '';
  isSubmit = false;
  isAdd = true;
  isLoad = true;
  isLoadStation = false;
  isShow = false;

  busStationId: any;

  busStations: BusStation[] = [] as BusStation[];
  busStationsSearch: BusStation[] = [] as BusStation[];

  busStationForm!: FormGroup;
  busStationRevenueForm!: FormGroup;

  lineChartData: ChartDataSets[] = [
    {
      data: [],
      label: '',
    },
  ];
  lineChartLabels: Label[] = [
    'Tháng 1',
    'Tháng 2',
    'Tháng 3',
    'Tháng 4',
    'Tháng 5',
    'Tháng 6',
    'Tháng 7',
    'Tháng 8',
    'Tháng 9',
    'Tháng 10',
    'Tháng 11',
    'Tháng 12',
  ];
  lineChartOptions: NewType & { annotation?: any } = {
    responsive: true,
  };
  lineChartColors: Color[] = [
    {
      borderColor: '#ef5222;',
      backgroundColor: 'rgba(255,0,0,0.3)',
    },
  ];

  lineChartLegend = true;
  lineChartType: any = 'line';
  lineChartPlugins = [];

  constructor(
    private busStationService: BusStationServiceService,
    private fb: FormBuilder,
    private toastr: ToastrService,
    private imageSerive: ImageDriveService
  ) {}

  ngOnInit(): void {
    this.year = new Date().getFullYear();

    this.onFormInit();
    this.onRevenueFormInit();

    this.onGetAllBusStation();
  }

  onChangeBusStation($event: any) {
    let bus = this.busStationsSearch.find((b) => b.id == $event.value);

    this.busStationRevenueForm.controls.busStationId.setValue($event.value);

    this.onGetBusStationRevenue(
      this.year,
      this.busStationRevenueForm.controls.busStationId.value
    );

    this.busStationRevenueForm.controls.address.setValue(bus?.dia_chi);
  }

  onSearch(search: string) {
    this.busStations = this.busStationsSearch;
    search = search
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '')
      .replace(/đ/g, 'd')
      .replace(/Đ/g, 'D');

    let busSearchByCity: BusStation[] = [] as BusStation[];
    let busSearchByBusStation: BusStation[] = [] as BusStation[];

    for (let i of this.busStations) {
      let city = i.thanh_pho;

      city = city
        .normalize('NFD')
        .replace(/[\u0300-\u036f]/g, '')
        .replace(/đ/g, 'd')
        .replace(/Đ/g, 'D');

      let busStation = i.ben_toi;
      busStation = busStation
        .normalize('NFD')
        .replace(/[\u0300-\u036f]/g, '')
        .replace(/đ/g, 'd')
        .replace(/Đ/g, 'D');

      if (city.toLowerCase().includes(search.toLowerCase())) {
        busSearchByCity.push(i);
      }
      if (busStation.toLowerCase().includes(search.toLowerCase())) {
        busSearchByBusStation.push(i);
      }
    }

    let result = busSearchByCity.concat(busSearchByBusStation);
    this.busStations = this.onUniqueArray(result);
  }

  onUniqueArray(array: BusStation[]) {
    var a = array.concat();
    for (var i = 0; i < a.length; ++i) {
      for (var j = i + 1; j < a.length; ++j) {
        if (a[i] === a[j]) a.splice(j--, 1);
      }
    }

    return a;
  }

  onPreviousYear() {
    if (this.year == 2020) {
      this.toastr.info('Công ty thành lập năm 2020');
      return;
    }
    this.year = this.year - 1;
    this.onGetBusStationRevenue(
      this.year,
      this.busStationRevenueForm.controls.busStationId.value
    );
  }

  onNextYear() {
    if (this.year == new Date().getFullYear()) return;
    this.year = this.year + 1;
    this.onGetBusStationRevenue(
      this.year,
      this.busStationRevenueForm.controls.busStationId.value
    );
  }

  onFormInit() {
    this.busStationForm = this.fb.group({
      busStationName: new FormControl(null, Validators.required),
      address: new FormControl(null, Validators.required),
      city: new FormControl(null, Validators.required),
    });
  }

  onRevenueFormInit() {
    this.busStationRevenueForm = this.fb.group({
      busStationId: new FormControl(null),
      ticket: new FormControl(0),
      amount: new FormControl(0),
      address: new FormControl(''),
    });
  }

  onSetValueForm(data: any) {
    this.busStationId = data.id;
    this.busStationForm.patchValue({
      busStationName: data.ben_toi,
      address: data.dia_chi,
      city: data.thanh_pho,
    });
    this.srcImage = data.picture;
    this.picture = data.picture;
    this.isLoadStation = false;
  }

  onResetValueForm() {
    this.busStationForm.patchValue({
      busStationName: null,
      address: null,
      city: null,
    });
    this.srcImage = '';
    this.picture = '';
  }

  onSubmit() {
    this.isSubmit = true;

    let busStation = {
      ten_ben: this.busStationForm.controls.busStationName.value,
      dia_chi: this.busStationForm.controls.address.value,
      thanh_pho: this.busStationForm.controls.city.value,
      picture: this.picture,
    };

    if (this.isAdd) {
      if(this.busStationForm.invalid){
        this.toastr.warning("Xin hãy điền đẩy đủ thông tin!");
        return;
      }
      this.onCreateBusStation(busStation);
    } else {
      this.onPostBusStation(busStation);
    }
  }

  onCreateBusStation(busStation: any) {
    this.busStationService.postCreateStation(busStation).subscribe(
      (data) => {
        this.toastr.success('Thêm bến xe thành công');
        this.reset();
        this.srcImage = "";
        this.onGetAllBusStation();
      },
      () => {
        this.toastr.warning('Thêm bến thất bại');
      }
    );
  }

  onPostBusStation(busStation: any) {
    this.busStationService
      .postUpdateStation(this.busStationId, busStation)
      .subscribe(
        (data) => {
          this.onGetAllBusStation();
          this.toastr.success('Chỉnh sửa bến xe thành công');
        },
        (error) => {
          this.toastr.warning('Chỉnh sửa bến thất bại');
        }
      );
  }

  reset() {
    this.myInputVariable.nativeElement.value = "";
  }

  onEditBusStation(busStation: BusStation) {
    this.isAdd = false;
    this.isShow = true;
    this.isLoadStation = true;
    this.onSetValueForm(busStation);
  }

  onAddBusStation() {
    this.isAdd = true;
    this.isShow = true;
    this.onResetValueForm();
  }

  onDeleteBusStation(busStationId: number) {}

  fileSelected: any;

  imgeChanged(obj: any) {
    this.fileSelected = <File>obj.target.files[0];
    if (obj.target.files && obj.target.files[0]) {
      const file = obj.target.files[0];

      const reader = new FileReader();
      reader.onload = (e) => (this.srcImage = reader.result);

      reader.readAsDataURL(file);
    }
    this.onImageFromDriver(this.fileSelected);
  }

  onImageFromDriver(file: any) {
    this.imageSerive
      .uploadImage(file)
      .pipe()
      .subscribe(
        (res) => {
          this.picture = `https://drive.google.com/uc?id=${res.id}`;
        },
        (error) => {
          console.log(error);
        }
      );
  }

  onGetAllBusStation() {
    this.isLoad = true;
    this.busStationService.getAllBusStation().subscribe((data) => {
      this.busStations = data.data;
      this.busStationsSearch = data.data;

      this.lineChartData[0].label =
        'Doanh thu của bến ' + this.busStationsSearch[0].ben_toi;

      this.busStationRevenueForm.controls.address.setValue(
        this.busStations[0].dia_chi
      );
      this.busStationRevenueForm.controls.busStationId.setValue(
        this.busStationsSearch[0].id
      );

      this.onGetBusStationRevenue(this.year, this.busStations[0].id);
    });
  }

  onGetBusStationRevenue(year: number, busStationId: number) {
    this.isLoad = true;
    this.busStationService
      .getBusStationRevenue(year, busStationId)
      .subscribe((data) => {
        this.busStationRevenueForm.controls.amount.setValue(data.data.total);
        this.lineChartData[0].data = [];

        let ticket = 0;

        for (let b of data.data.list_data) {
          ticket += b.total_ve;
          this.lineChartData[0].data.push(b.total_amount);
        }

        this.busStationRevenueForm.controls.ticket.setValue(ticket);
        this.isLoad = false;
      });
  }
}
