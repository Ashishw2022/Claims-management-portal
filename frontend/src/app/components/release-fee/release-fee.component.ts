import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Fee } from 'src/app/Fee';
import { AppService } from 'src/app/app.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-release-fee',
  templateUrl: './release-fee.component.html',
  styleUrls: ['./release-fee.component.css']
})
export class ReleaseFeeComponent implements OnInit{


  fee?: Fee
  data: any
  constructor(private service: AppService, private route: ActivatedRoute, private router : Router,private http: HttpClient) { }

  form = new FormGroup({
    fee_Id: new FormControl()
      })

    ngOnInit(): void {
        this.data=this.form.value
        console.log(this.data);
       
      }
    submit(){
      let id =this.form.value.fee_Id
      this.service.getFeeDetailById(id).subscribe(data => {
        this.fee = data
        console.log(this.fee)
         
          this.service.updateFeeDetails(id,this.data).subscribe(data => {
            console.log(data)
            setTimeout(()=>{
              window.location.reload();
            }, 100);
            
            Swal.fire({
              icon: 'success',
              title: `hurray`,
              text: 'Fee details Updated successfully',
              showConfirmButton: true,
            });
            
          });
        });
 
              this.router.navigate(['/']);
              
          

            
            }
        


      
}





