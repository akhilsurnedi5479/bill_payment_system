import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentGatewayGpayComponent } from './payment-gateway-gpay.component';

describe('PaymentGatewayGpayComponent', () => {
  let component: PaymentGatewayGpayComponent;
  let fixture: ComponentFixture<PaymentGatewayGpayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymentGatewayGpayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentGatewayGpayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
