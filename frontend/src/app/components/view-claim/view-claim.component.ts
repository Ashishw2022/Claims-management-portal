import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';


@Component({
  selector: 'app-view-claim',
  templateUrl: './view-claim.component.html',
  styleUrls: ['./view-claim.component.css']
})
export class ViewClaimComponent {

  claims: any[] | undefined
  url: string = "http://localhost:8081/";


  constructor(private service: AppService, private router: Router) { 
  }

  ngOnInit(): void {
    this.service.getClaims().subscribe(data => {
      const filteredClaim = data.filter(claim => claim.claim_Status === "open");
      this.claims = filteredClaim;     
    });
  }
  updateClaim(claim_Id: String){
    this.router.navigate(['update', claim_Id]);
  }
}
