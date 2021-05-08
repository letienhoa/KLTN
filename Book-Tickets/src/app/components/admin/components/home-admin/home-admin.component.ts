import { Component, OnInit } from '@angular/core';

import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip, SingleDataSet } from 'ng2-charts';



@Component({
  selector: 'app-home-admin',
  templateUrl: './home-admin.component.html',
  styleUrls: ['./home-admin.component.css']
})
export class HomeAdminComponent implements OnInit, DrawChart {

  pieChartOptions: ChartOptions = {
    responsive: true,
    legend:{
      position:'right'
    },
    title:{
      text:"Đồ thị phần trăm tài khoản",
      display:true,
      position:'left',
      fontColor:'#00613d',
      fontSize: 20,
      fontStyle:'bold'
    }
    
  };
  pieChartLabels: Label[] = [['SciFi'], ['Drama'], 'Comedy'];
  pieChartData: SingleDataSet = [30, 50, 20];
  pieChartType: ChartType = 'pie'; 
  pieChartLegend = true;
  pieChartPlugins = [];
  doughnutChartColors: Color[] = [
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]}
  ];

  constructor() {
    monkeyPatchChartJsTooltip();
    monkeyPatchChartJsLegend();
   }

  ngOnInit(): void {
    
  }


  barChartOptions: ChartOptions = {
    responsive: true,
    
  };
  barChartLabels: Label[] = ['Apple', 'Banana', 'Kiwifruit'];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];

  barChartData: ChartDataSets[] = [
    {data: [45, 37, 60]}
    
  ];
 
}

class DrawChart {

}

