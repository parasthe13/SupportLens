# Order Support Troubleshooting Guide (Sample / Synthetic)

> This is fictional sample content for testing SupportLens. It does not describe any real system.

## Order stuck in "Processing"

An order can remain in the **Processing** state when the payment authorization
has succeeded but the fulfillment service has not yet picked up the order event.

Checklist:

1. Confirm the payment shows **Authorized** in the Payments panel.
2. Check the fulfillment queue for the order id. If it is missing, the order
   event was likely not published.
3. Re-publish the order event from the Admin console using **Actions → Re-publish**.
4. If it is still stuck after 15 minutes, escalate to the Fulfillment team.

## Order stuck in "Pending Payment"

This means we are waiting on the payment provider. Common causes:

- The customer's card issuer is holding the transaction for review.
- The 3-D Secure challenge was not completed.

Ask the customer to complete any verification sent by their bank. Pending
payment orders auto-cancel after **24 hours** if not authorized.

## Refund not received by customer

Refunds are issued to the original payment method and can take **5–10 business
days** to appear, depending on the customer's bank. Verify the refund shows
**Completed** in the Payments panel. If it shows **Failed**, re-issue the refund
and confirm the card is still valid.

## Cannot cancel a shipped order

Once an order has shipped it **cannot be cancelled**. The customer should start a
**return** instead. Provide the return label from **Order → Returns → Create label**.

## Tracking number not updating

Carrier tracking can take up to **48 hours** to show the first scan after a label
is created. If there is no movement after 48 hours, open a carrier trace from
**Order → Shipment → Trace**.
