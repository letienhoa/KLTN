import { Component, OnInit } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label } from 'ng2-charts';

declare var $:any

@Component({
  selector: 'app-bus-route',
  templateUrl: './bus-route.component.html',
  styleUrls: ['./bus-route.component.css']
})
export class BusRouteComponent implements OnInit {

  isEnable = true;
   
  barChartOptions: ChartOptions = {
    responsive: true,
    scales:{
      xAxes:[{
        stacked:true
      }],
      yAxes:[{
        stacked:true
      }]
    }
  };
  barChartLabels: Label[] = ['Apple', 'Banana', 'Kiwifruit','Apple', 'Banana', 'Kiwifruit','Apple', 'Banana', 'Kiwifruit','Apple', 'Banana', 'Kiwifruit'];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];

  barChartData: ChartDataSets[] = [
    {data: [45, 37, 120,45, 37, 120,45, 37, 120,45, 37, 120],label: 'Series A', stack: 'a'},
    {data: [45, 37, 120,45, 37, 120,45, 37, 120,45, 37, 120],label: 'Series A', stack: 'a'}
    
  ];

  constructor() { }

  ngOnInit(): void {
  }

  load(){

  }

  onEnable(){
    this.isEnable = !this.isEnable;
    $("#myModal").show();
  }

  onCheck(type:number){
    if(type==1)
      $("#show-check").show();
    else 
      $("#show-check").hide();
  }

}
