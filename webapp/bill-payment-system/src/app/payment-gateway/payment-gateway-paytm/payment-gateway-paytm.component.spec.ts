import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentGatewayPaytmComponent } from './payment-gateway-paytm.component';

describe('PaymentGatewayPaytmComponent', () => {
  let component: PaymentGatewayPaytmComponent;
  let fixture: ComponentFixture<PaymentGatewayPaytmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymentGatewayPaytmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentGatewayPaytmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
