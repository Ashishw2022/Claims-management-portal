import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ClaimService } from 'src/app/services/irda.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-irda',
  templateUrl: './irda.component.html',
  styleUrls: ['./irda.component.css']
})
export class IrdaComponent implements OnInit{
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

  ngOnInit(): void {
  }
  onSubmit(form:NgForm){
    console.log("____________________________________")
    console.log( form.value.mon,form.value.year);
    this.claimService
        .pendingClaim(
         form.value.mon, form.value.year
          )
        .subscribe(
          (response) => {

            Swal.fire({
              icon: 'success',
              title: 'Report Generated',
              showConfirmButton: true,
            })
          },
          (errorMessage) => {
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Please Check Your Details and try again',
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
      .pendingPayment(
        form.value.mont,form.value.yea
        )
      .subscribe(
        (response) => {

          Swal.fire({
            icon: 'success',
            title: 'Report Generated',
            showConfirmButton: true,
          })
        },
        (errorMessage) => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Please Check Your Details and try again',
            // footer: '<a href="">Why do I have this issue?</a>'
          })
          console.log(errorMessage);
        }
      );
}


}
