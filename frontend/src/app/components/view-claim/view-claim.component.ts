import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-view-claim',
  templateUrl: './view-claim.component.html',
  styleUrls: ['./view-claim.component.css'],
  providers: [DatePipe], // add the DatePipe to the providers array

})
export class ViewClaimComponent {

  claims: any[] | undefined
  url: string = "http://localhost:8081/";


  constructor(private service: AppService, private router: Router,private datePipe: DatePipe) { 
  }

  ngOnInit(): void {
    this.service.getClaims().subscribe(data => {
      const filteredClaim = data.filter(claim => claim.claim_Status === "open");
      this.claims = filteredClaim;
      
      
    });
  }

  updateClaim(claim_Id: String){
    this.router.navigate(['update', claim_Id]);
    // setTimeout(()=>{
    //      window.location.reload();
    //   }, 100);
  }

  
  


}
