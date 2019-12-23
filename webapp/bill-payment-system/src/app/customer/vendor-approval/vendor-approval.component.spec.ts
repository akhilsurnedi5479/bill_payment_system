import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorApprovalComponent } from './vendor-approval.component';

describe('VendorApprovalComponent', () => {
  let component: VendorApprovalComponent;
  let fixture: ComponentFixture<VendorApprovalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VendorApprovalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VendorApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
