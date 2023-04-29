import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';
import { ClaimService } from 'src/app/services/irda.service';


@Component({
  selector: 'app-show-reports',
  templateUrl: './show-reports.component.html',
  styleUrls: ['./show-reports.component.css']
})
export class ShowReportsComponent implements OnInit {
  showTable: boolean = false;
  showTable2: boolean = false;
  month:any;
  year:any;
  allItems = [
    { month:1,mont:'Janurary' },
    { month:2,mont:'February' },
    { month:3, mont:'March' },
    { month:4,mont:'April' },
    { month:5,mont:'May' },
    { month:6,mont:'June' },
    { month:7,mont:'July' },
    { month:8,mont:'August' },
    { month:9,mont:'September' },
    { month:10,mont:'October' },
    { month:11,mont:'November' },
    { month:12,mont:'December' },
    
  
  ];

  constructor(private claimService: ClaimService,
    private router: Router,
    private route: ActivatedRoute) { }
    claimReport: any=[];
    paymentReport:any=[];


  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  onSubmit(form:NgForm){
    console.log("____________________________________")
    console.log( form.value.mon,form.value.year);
    this.claimService
        .showPendingClaim(
         form.value.mon, form.value.year
          )
        .subscribe(
          (response) => {
            this.showTable=true
            this.showTable2=false
            this.claimReport.push(response);
            console.log(this.claimReport)
            Swal.fire({
              icon: 'success',
              title: 'Report Fetched',
              showConfirmButton: true,
            })
          },
          (errorMessage) => {
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'No Reports Generated \n Contact IRDA for Reports',
              // footer: '<a href="">Why do I have this issue?</a>'
            })
            console.log(errorMessage);
          }
        );
  }
  onSubmit2(form:NgForm){
    console.log("____________________________________")
    console.log( form.value.mont,form.value.yea);
    this.claimService
        .showPendingPayment(
          form.value.mont,form.value.yea
          )
        .subscribe(
          (response) => {
            
            this.paymentReport=response;
            console.log(this.paymentReport.length)
          if(this.paymentReport.length==0){
            console.log(this.paymentReport.length)
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'No Reports Generated \n Contact IRDA for Reports',
              // footer: '<a href="">Why do I have this issue?</a>'
            })
          }
          else if(this.paymentReport.length!=0){
            console.log(this.paymentReport)
            this.showTable=false;
            this.showTable2=true;
            Swal.fire({
              icon: 'success',
              title: 'Report Fetched',
              showConfirmButton: true,
            })
          }
           
          },
          
        );
  }

}
