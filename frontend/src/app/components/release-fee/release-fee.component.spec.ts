import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReleaseFeeComponent } from './release-fee.component';

describe('ReleaseFeeComponent', () => {
  let component: ReleaseFeeComponent;
  let fixture: ComponentFixture<ReleaseFeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReleaseFeeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReleaseFeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
