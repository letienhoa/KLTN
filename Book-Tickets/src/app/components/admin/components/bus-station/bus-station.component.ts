import { Component, OnInit } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label } from 'ng2-charts';

@Component({
  selector: 'app-bus-station',
  templateUrl: './bus-station.component.html',
  styleUrls: ['./bus-station.component.css']
})
export class BusStationComponent implements OnInit {

  
  barChartOptions: ChartOptions = {
    responsive: true,
  
    
  };
  barChartLabels: Label[] = ['Apple', 'Banana', 'Kiwifruit','Apple', 'Banana', 'Kiwifruit','Apple', 'Banana', 'Kiwifruit','Apple', 'Banana', 'Kiwifruit'];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];

  barChartData: ChartDataSets[] = [
    {data: [45, 37, 120,45, 37, 120,45, 37, 120,45, 37, 120]}
    
  ];

  doughnutChartColors: Color[] = [
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]},
    {backgroundColor:["#9E120E","#FF5800","#FFB414"]}
  ];

  constructor() { }

  ngOnInit(): void {
  }

}